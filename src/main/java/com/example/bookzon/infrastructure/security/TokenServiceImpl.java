package com.example.bookzon.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.bookzon.application.gateways.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;


@Service
public class TokenServiceImpl implements TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    @Value("${api.security.refreshToken.expiration}")
    private Long refreshTokenExpiration;

    @Value("${api.security.token.expiration}")
    private Long accessTokenExpiration;

    public String generateToken(UUID userId,String username){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(username)
                    .withClaim("userId", userId.toString())
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
    public String generateRefreshToken(UUID userId,String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String refreshToken = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(username)
                    .withClaim("userId", userId.toString())
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
    public UUID getUserIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build();

            DecodedJWT jwt = verifier.verify(token);

            String userIdStr = jwt.getClaim("userId").asString();

            UUID userId = UUID.fromString(userIdStr);
            return userId;
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Invalid token or token expired", e);
        }
    }
    private Instant genExpirationDate(long expirationSeconds){
        return LocalDateTime.now().plusSeconds(expirationSeconds).toInstant(ZoneOffset.UTC);
    }
}