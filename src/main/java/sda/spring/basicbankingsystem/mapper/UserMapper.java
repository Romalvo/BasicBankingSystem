package sda.spring.basicbankingsystem.mapper;

import org.springframework.stereotype.Component;
import sda.spring.basicbankingsystem.dto.request.RegisterRequestDto;
import sda.spring.basicbankingsystem.dto.response.UserProfileDto;
import sda.spring.basicbankingsystem.entity.User;

import java.time.LocalDateTime;


@Component
public class UserMapper {

    public static UserProfileDto toUserProfile(User user) {
        return new UserProfileDto()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setFirstname(user.getFirstname())
                .setLastname(user.getLastname())
                .setEmail(user.getEmail());
    }
    

    public static User fromRegisterToUserEntity(RegisterRequestDto registerUserRequestDto) {
        return new User()
                .setEmail(registerUserRequestDto.getEmail())
                .setUsername(registerUserRequestDto.getUsername())
                .setFirstname(registerUserRequestDto.getFirstname())
                .setLastname(registerUserRequestDto.getLastname())
                .setPassword(registerUserRequestDto.getPassword())
                .setCreatedAt(LocalDateTime.now());
    }

    public UserProfileDto fromEntityToUserProfile(User owner) {
        return new UserProfileDto()
                .setId(owner.getId())
                .setFirstname(owner.getFirstname())
                .setLastname(owner.getLastname())
                .setUsername(owner.getUsername())
                .setEmail(owner.getEmail());
    }
}
