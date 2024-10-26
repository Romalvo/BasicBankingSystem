package sda.spring.basicbankingsystem.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sda.spring.basicbankingsystem.entity.User;
import sda.spring.basicbankingsystem.repository.UserRepository;

import java.util.Collections;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;



    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username ));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList()
        );
    }

}
