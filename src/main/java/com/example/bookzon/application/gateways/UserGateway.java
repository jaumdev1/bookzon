package com.example.bookzon.application.gateways;

import com.example.bookzon.domain.entities.User;


public interface UserGateway {
    User createUser(User user);
    User findByUsername(String username);
}