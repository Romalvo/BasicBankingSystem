package sda.spring.basicbankingsystem.mapper;


import org.springframework.stereotype.Component;
import sda.spring.basicbankingsystem.dto.response.OpenAccountResponseDto;
import sda.spring.basicbankingsystem.entity.Account;


@Component
public class AccountMapper {

    private final UserMapper userMapper;
    private final BranchMapper branchMapper;

    public AccountMapper(UserMapper userMapper, BranchMapper branchMapper) {
        this.userMapper = userMapper;
        this.branchMapper = branchMapper;
    }

//    public Account fromOpenAccountRequestToEntity(OpenAccountDto openAccountDto) {
//        return new Account()
//                .setBalance(BigDecimal.ZERO)
//                .setStatus(AccountStatus.OPEN);
//    }

    public OpenAccountResponseDto fromEntityToOpenAccountResponseDto(Account account) {
        return new OpenAccountResponseDto()
                .setBalance(account.getBalance())
                .setBranch(branchMapper.fromEntityToCreateBranchResponseDto(account.getBranch()))
                .setOwner(userMapper.fromEntityToUserProfile(account.getOwner()))
                .setOwnerId(account.getOwner().getId())
                .setIban(account.getIban());
    }
}
