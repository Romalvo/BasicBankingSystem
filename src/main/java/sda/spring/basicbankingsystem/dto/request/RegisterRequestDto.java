package sda.spring.basicbankingsystem.dto.request;


import lombok.Data;

@Data
public class RegisterRequestDto {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
}
