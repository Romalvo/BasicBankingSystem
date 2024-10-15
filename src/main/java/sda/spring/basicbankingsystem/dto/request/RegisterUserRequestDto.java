package sda.spring.basicbankingsystem.dto.request;


import lombok.Data;

@Data
public class RegisterUserRequestDto {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
}
