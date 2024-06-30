package com.example.bookzon.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.bookzon.application.gateways.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenServiceImpl implements TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    @Value("${api.security.refreshToken.expiration}")
    private Long refreshTokenExpiration;

    @Value("${api.security.token.expiration}")
    private Long accessTokenExpiration;

    public String generateToken(String username){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(username)
                    .withExpiresAt(genExpirationDate(accessTokenExpiration))
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }
    public String generateRefreshToken(String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String refreshToken = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(username)
                    .withExpiresAt(genExpirationDate(refreshTokenExpiration))
                    .sign(algorithm);
            return refreshToken;
        } catch (Exception e) {
            throw new RuntimeException("Error while generating refresh token", e);
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            return JWT.decode(token).getSubject();
        } catch (JWTDecodeException exception) {
            throw new RuntimeException("Error decoding token and extracting username", exception);
        }
    }
    private Instant genExpirationDate(long expirationSeconds){
        return LocalDateTime.now().plusSeconds(expirationSeconds).toInstant(ZoneOffset.UTC);
    }
}