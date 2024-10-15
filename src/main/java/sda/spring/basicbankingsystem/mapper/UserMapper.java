package sda.spring.basicbankingsystem.mapper;

import sda.spring.basicbankingsystem.dto.request.RegisterUserRequestDto;
import sda.spring.basicbankingsystem.dto.response.UserProfileDto;
import sda.spring.basicbankingsystem.entity.User;

import java.time.LocalDateTime;

public class UserMapper {

    public static UserProfileDto toUserProfile(User user) {
        return new UserProfileDto()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail());
    }

    public static User fromRegisterToUserEntity(RegisterUserRequestDto registerUserRequestDto) {
        return new User()
                .setEmail(registerUserRequestDto.getEmail())
                .setUsername(registerUserRequestDto.getUsername())
                .setFirstName(registerUserRequestDto.getFirstname())
                .setLastName(registerUserRequestDto.getLastname())
                .setPassword(registerUserRequestDto.getPassword())
                .setCreatedAt(LocalDateTime.now());
    }
}
