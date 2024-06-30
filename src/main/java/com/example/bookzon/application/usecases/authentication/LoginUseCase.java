package com.example.bookzon.application.usecases.authentication;

import com.example.bookzon.application.gateways.AuthenticationGateway;
import com.example.bookzon.application.gateways.UserGateway;
import com.example.bookzon.domain.entities.User;
import com.example.bookzon.infrastructure.dtos.LoginResponseDTO;

public class LoginUseCase {
    private final AuthenticationGateway authenticationGateway;

    public LoginUseCase(AuthenticationGateway authenticationGateway){
    this.authenticationGateway = authenticationGateway;
    }
    public LoginResponseDTO execute(User user){
        return authenticationGateway.Login(user.getUsername(), user.getPassword());
    }
}
