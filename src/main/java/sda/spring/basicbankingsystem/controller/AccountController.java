package sda.spring.basicbankingsystem.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.spring.basicbankingsystem.dto.request.OpenAccountDto;
import sda.spring.basicbankingsystem.dto.response.OpenAccountResponseDto;
import sda.spring.basicbankingsystem.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public OpenAccountResponseDto openAccount(@RequestBody OpenAccountDto openAccountDto) {
        return this.accountService.openAccount(openAccountDto);

    }
}
