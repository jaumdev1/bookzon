package com.example.bookzon.application.gateways;

import com.example.bookzon.domain.entities.User;


public interface UserGateway {
   public User createUser(User user);
   public User findByUsername(String username);
}