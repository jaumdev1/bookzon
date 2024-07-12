package com.example.bookzon.application.usecases.user;

import com.example.bookzon.application.gateways.UserGateway;
import com.example.bookzon.domain.entities.User;

import java.util.UUID;

public class GetUserUseCase {
    private final UserGateway userGateway;

    public GetUserUseCase(UserGateway userGateway){
        this.userGateway= userGateway;
    }
    public User execute(UUID userId) throws Exception {
        return userGateway.findById(userId).orElseThrow(() -> new Exception("User not found"));
    }

}
