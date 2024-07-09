package com.example.bookzon.application.usecases.authentication;

import com.example.bookzon.application.gateways.AuthenticationGateway;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AuthenticateUserUseCase {

    private final AuthenticationGateway authenticationGateway;

    public AuthenticateUserUseCase(AuthenticationGateway authenticationGateway){
        this.authenticationGateway = authenticationGateway;
    }
    public Authentication execute(String username, String password) {
        return authenticationGateway.authenticate(username, password);
    }
}