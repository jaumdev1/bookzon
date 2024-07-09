package com.example.bookzon.application.gateways;

import java.util.UUID;

public interface TokenService {
    String generateToken(UUID userId,String username);

    String generateRefreshToken(UUID userId,String username);
    String validateToken(String token);
    String getUsernameFromToken(String token);

    UUID getUserIdFromToken(String token);

}
