package com.example.bookzon.application.gateways;

import com.example.bookzon.domain.entities.User;

import java.util.Optional;
import java.util.UUID;


public interface UserGateway {
    User createUser(User user);
    User findByUsername(String username);

    Optional<User> findById(UUID userId);

}