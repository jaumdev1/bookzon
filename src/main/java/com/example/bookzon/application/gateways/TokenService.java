package com.example.bookzon.application.gateways;

public interface TokenService {
    String generateToken(String username);

    String generateRefreshToken(String username);
    String validateToken(String token);
    String getUsernameFromToken(String token);
}
