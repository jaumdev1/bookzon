package com.example.bookzon.infrastructure.security.UserIdStrategy.Strategy;

import com.example.bookzon.domain.entities.User;
import com.example.bookzon.infrastructure.security.UserIdStrategy.UserIdStrategy;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SessionUserIdStrategy implements UserIdStrategy {

    private HttpSession session;

    public SessionUserIdStrategy(HttpSession session){
        this.session =  session;
    }
    @Override
    public UUID getCurrentUserId() {
         SecurityContext securityContext = (SecurityContext) (session).getAttribute("SPRING_SECURITY_CONTEXT");
        if (securityContext != null && securityContext.getAuthentication() != null) {
            Authentication authentication = securityContext.getAuthentication();
            User userDetails = (User) authentication.getPrincipal();
            return userDetails.getId();
        }
        return null;
    }
}