package sda.spring.basicbankingsystem.mapper;

import org.springframework.stereotype.Component;
import sda.spring.basicbankingsystem.dto.response.TransactionResponseDto;
import sda.spring.basicbankingsystem.entity.Transaction;

@Component
public class TransactionMapper {

    private final AccountMapper accountMapper;

    public TransactionMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public TransactionResponseDto fromEntityToResponseDto(Transaction transaction) {
        return new TransactionResponseDto()
                .setId(transaction.getId())
                .setAmount(transaction.getAmount())
                .setCreatedAt(transaction.getCreatedAt())
                .setSenderAccount(accountMapper.fromEntityToOpenAccountResponseDto(transaction.getSenderAccount()))
                .setRecipientAccount(accountMapper.fromEntityToOpenAccountResponseDto(transaction.getRecipientAccount())
                );
    }
}
