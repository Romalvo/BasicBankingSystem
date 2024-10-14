package sda.spring.basicbankingsystem.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sda.spring.basicbankingsystem.dto.response.TokenResponseDto;
import sda.spring.basicbankingsystem.dto.response.UserProfileDto;
import sda.spring.basicbankingsystem.entity.User;
import sda.spring.basicbankingsystem.mapper.UserMapper;
import sda.spring.basicbankingsystem.repository.UserRepository;
import sda.spring.basicbankingsystem.util.JwtUtil;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    public AuthController (AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // Register a new user

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    // Login and return JWT Token

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        String token = jwtUtil.generateToken(user.getUsername());

        // If user in the token == sample user then return access denied

        return ResponseEntity.ok(new TokenResponseDto(token));
    }

    // Get user profile (secured endpoint)

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getProfile() throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.isAuthenticated()) {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username)  // implement retrieval logic
                    .orElseThrow(()-> new RuntimeException("User Not Found"));
            return ResponseEntity.ok(UserMapper.toUserProfile(user));
        }

        throw new AccessDeniedException("Access denied. User not found");

    }

    // Logout is handled client-side: you can invalidate the token if needed

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout successful");
    }
}
