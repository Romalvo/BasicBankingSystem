package sda.spring.basicbankingsystem.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sda.spring.basicbankingsystem.dto.request.LoginRequestDto;
import sda.spring.basicbankingsystem.dto.request.RegisterRequestDto;
import sda.spring.basicbankingsystem.dto.response.TokenResponseDto;
import sda.spring.basicbankingsystem.dto.response.UserProfileDto;
import sda.spring.basicbankingsystem.service.UserService;
import sda.spring.basicbankingsystem.util.JwtUtil;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtUtil jwtUtil;

    public AuthController (AuthenticationManager authenticationManager,
                           UserService userService,
                           JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // Register a new user

    @PostMapping("/register")
    public ResponseEntity<UserProfileDto> register(@RequestBody RegisterRequestDto registerUserRequestDto) {

        UserProfileDto response = userService.registerUser(registerUserRequestDto);
        return ResponseEntity.ok().body(response);
    }

    // Login and return JWT Token

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

        String token = jwtUtil.generateToken(loginRequestDto.getUsername());

        // If user in the token == sample user then return access denied

        return ResponseEntity.ok(new TokenResponseDto(token));
    }

    // Get user profile (secured endpoint)

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getProfile() {
        return ResponseEntity.ok(userService.getLoggedInUserProfile());

    }

    // Logout is handled client-side: you can invalidate the token if needed

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout successful");
    }
}
