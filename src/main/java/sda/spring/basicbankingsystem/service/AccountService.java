package sda.spring.basicbankingsystem.service;


import org.iban4j.CountryCode;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sda.spring.basicbankingsystem.dto.request.OpenAccountDto;
import sda.spring.basicbankingsystem.dto.response.OpenAccountResponseDto;
import sda.spring.basicbankingsystem.entity.Account;
import sda.spring.basicbankingsystem.entity.Branch;
import sda.spring.basicbankingsystem.entity.User;
import sda.spring.basicbankingsystem.mapper.AccountMapper;
import sda.spring.basicbankingsystem.mapper.UserMapper;
import sda.spring.basicbankingsystem.repository.AccountRepository;
import sda.spring.basicbankingsystem.repository.BranchRepository;
import sda.spring.basicbankingsystem.repository.UserRepository;
import sda.spring.basicbankingsystem.util.IbanGenerator;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final IbanGenerator ibanGenerator;
    private final BranchRepository branchRepository;

    public AccountService(AccountRepository accountRepository,
                          AccountMapper accountMapper,
                          UserRepository userRepository,
                          UserMapper userMapper,
                          IbanGenerator ibanGenerator,
                          BranchRepository branchRepository) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.ibanGenerator = ibanGenerator;
        this.branchRepository = branchRepository;
    }

    public OpenAccountResponseDto openAccount(OpenAccountDto openAccountDto) {

        Account account = accountMapper.fromOpenAccountRequestToEntity(openAccountDto);

        //1-set logged in user as account user

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.isAuthenticated()){
            String username = authentication.getName();
            User user = userRepository.findByUsername(username)
                    .orElseThrow(()-> new RuntimeException("User Not Found"));
            account.setOwner(user);
        }

        Branch branch = branchRepository.findById(openAccountDto.getBranchId()).orElseThrow(
                RuntimeException::new
        );
        // 2 - Generate IBAN

        account.setIban(ibanGenerator.generate(
                CountryCode.valueOf(branch.getCountry()),
                "243254354234"
        ).toFormattedString());

        account = accountRepository.save(account);

        return accountMapper.fromEntityToOpenAccountResponseDto(account);
    }
}
