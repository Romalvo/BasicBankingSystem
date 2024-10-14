package sda.spring.basicbankingsystem.mapper;

import sda.spring.basicbankingsystem.dto.response.UserProfileDto;
import sda.spring.basicbankingsystem.entity.User;

public class UserMapper {

    public static UserProfileDto toUserProfile(User user) {
        return new UserProfileDto()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail());
    }
}
