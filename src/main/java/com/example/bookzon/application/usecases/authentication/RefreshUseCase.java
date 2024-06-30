package com.example.bookzon.application.usecases.authentication;

import com.example.bookzon.application.gateways.AuthenticationGateway;
import com.example.bookzon.domain.entities.User;
import com.example.bookzon.infrastructure.dtos.LoginResponseDTO;
import com.example.bookzon.infrastructure.dtos.RefreshResponseDTO;
import com.example.bookzon.infrastructure.dtos.RegisterRequestDTO;

public class RefreshUseCase {

    private final AuthenticationGateway authenticationGateway;

    public RefreshUseCase(AuthenticationGateway authenticationGateway){
        this.authenticationGateway = authenticationGateway;
    }

    public RefreshResponseDTO execute(String refreshToken){
        return authenticationGateway.Refresh(refreshToken);
    }


}
