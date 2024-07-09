package com.example.bookzon.infrastructure.security.UserIdStrategy.Strategy;

import com.example.bookzon.application.gateways.TokenService;
import com.example.bookzon.infrastructure.security.UserIdStrategy.UserIdStrategy;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.UUID;
@Component
public class TokenUserIdStrategy implements UserIdStrategy {

    @Autowired
    private  TokenService tokenService;

    private final HttpServletRequest session;
    public TokenUserIdStrategy(HttpServletRequest session){
        this.session =session;
    }


    @Override
    public UUID getCurrentUserId() {
        String jwtToken = this.extractTokenFromHeader(session);
        if (jwtToken != null) {
            return tokenService.getUserIdFromToken(jwtToken);
        }
        return null;
    }
    public String extractTokenFromHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}