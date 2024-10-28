package sda.spring.basicbankingsystem.service;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sda.spring.basicbankingsystem.dto.request.RegisterRequestDto;
import sda.spring.basicbankingsystem.dto.response.UserProfileDto;
import sda.spring.basicbankingsystem.entity.User;
import sda.spring.basicbankingsystem.mapper.UserMapper;
import sda.spring.basicbankingsystem.repository.UserRepository;


@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;


    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;

    }

    public UserProfileDto registerUser(RegisterRequestDto registerUserRequestDto) {
        User user = userMapper.fromRegisterToUserEntity(registerUserRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        return userMapper.toUserProfile(user);

    }

    public UserProfileDto getLoggedInUserProfile() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.isAuthenticated()){
            String username = authentication.getName();
            User user = userRepository.findByUsername(username) //Implement retrieval logic
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return UserMapper.toUserProfile(user);
        }
        return null;
    }
}
