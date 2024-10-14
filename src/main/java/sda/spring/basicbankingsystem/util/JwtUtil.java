package sda.spring.basicbankingsystem.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    // Generate a SecretKey for HS256 (HMAC) algorithm
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expiration = 1000L * 60 * 60 * 24 * 30;  //1 month

    // 1h      = 1000 * 60 * 60
    // 24h     = 1000 * 60 * 60 * 24
    // 1 month = 1000 * 60 * 60 * 24 * 30

    // Generate a JWT Token for the user
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    // Create a token with claims

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey) // Signing with the secret key
                .compact();
    }

    // Extract username from the token

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Extract all claims from the token

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(secretKey) // This is valid for HS256
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Validate the token

    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // Check if the token is expired

    private Boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public String passwordHash (String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
