package sda.spring.basicbankingsystem.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sda.spring.basicbankingsystem.dto.request.IntraBankTransferRequestDto;
import sda.spring.basicbankingsystem.dto.response.TransactionResponseDto;
import sda.spring.basicbankingsystem.entity.Account;
import sda.spring.basicbankingsystem.entity.Transaction;
import sda.spring.basicbankingsystem.entity.User;
import sda.spring.basicbankingsystem.enums.AccountStatus;
import sda.spring.basicbankingsystem.enums.TransactionsStatus;
import sda.spring.basicbankingsystem.mapper.TransactionMapper;
import sda.spring.basicbankingsystem.repository.AccountRepository;
import sda.spring.basicbankingsystem.repository.TransactionRepository;
import sda.spring.basicbankingsystem.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository,
                              UserRepository userRepository,
                              TransactionMapper transactionMapper
    ) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.transactionMapper = transactionMapper;
    }

    public TransactionResponseDto makeIntraBankTransaction(IntraBankTransferRequestDto intraBankTransferRequestDto) {
        // 1 - Get the logged in request/user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User loggedInUser = userRepository.findByUsername(username)
                .orElseThrow(RuntimeException::new);

        // 2 - Make sure fromAccount is owned by the logged-in user, otherwise access denied.
        Account fromAccount = accountRepository.findById(intraBankTransferRequestDto.getFromAccount())
                .orElseThrow(RuntimeException::new);

        if(!fromAccount.getOwner().equals(loggedInUser)) {
            // Access denied exception
            throw new RuntimeException("You are not owner of this account");
        }
        // 3 - Make sure fromAccount has enough balance, otherwise throw exception
        //from account balance < transfer request amount
        if(fromAccount.getBalance().compareTo(intraBankTransferRequestDto.getAmount()) < 0) {
            //InsufficientFundException
            throw new RuntimeException("You dont't have enough balance to make this transaction");
        }
        // 4- make sure recipient account exists in our database and is ACTIVE

        Account toAccount = accountRepository.findById(intraBankTransferRequestDto.getToAccount())
                .orElseThrow(RuntimeException::new);

        if(toAccount.getStatus() != AccountStatus.ACTIVE) {
            throw new RuntimeException("You cannot transfer money to an account which is not ACTIVE");
        }

        // 5 - create a transaction record which decreases account balance of source account
        Transaction transaction = new Transaction();
        transaction.setAmount(intraBankTransferRequestDto.getAmount())
                .setStatus(TransactionsStatus.PENDING)
                .setSenderAccount(fromAccount)
                .setSenderAccountIBAN(fromAccount.getIban())
                .setRecipientAccount(toAccount)
                .setRecipientAccountIBAN(toAccount.getIban())
                .setCreatedAt(LocalDateTime.now());

        // Insert transaction
        transactionRepository.save(transaction);

        BigDecimal transferAmount = intraBankTransferRequestDto.getAmount();
        fromAccount.setBalance(fromAccount.getBalance().subtract(transferAmount));

        // Update the account
        accountRepository.save(fromAccount);

        toAccount.setBalance(toAccount.getBalance().add(transferAmount));
        accountRepository.save(toAccount);

        // 6 - Create another transaction record which increases account balance of target account
        return transactionMapper.fromEntityToResponseDto(transaction);
    }
}
