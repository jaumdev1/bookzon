package com.example.bookzon.infrastructure.security.UserIdStrategy.Factory;

import com.example.bookzon.domain.entities.User;
import com.example.bookzon.infrastructure.security.UserIdStrategy.Strategy.SessionUserIdStrategy;
import com.example.bookzon.infrastructure.security.UserIdStrategy.Strategy.TokenUserIdStrategy;
import com.example.bookzon.infrastructure.security.UserIdStrategy.UserIdStrategy;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class UserIdStrategyFactory {
    public UserIdStrategy getStrategy(HttpSession session) {
         return new SessionUserIdStrategy(session);
    }
    public UserIdStrategy getStrategy(HttpServletRequest session) {
        return new TokenUserIdStrategy(session);
    }
}