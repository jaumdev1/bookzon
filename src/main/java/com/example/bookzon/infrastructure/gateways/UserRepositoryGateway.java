package com.example.bookzon.infrastructure.gateways;

import com.example.bookzon.application.gateways.UserGateway;
import com.example.bookzon.domain.entities.User;
import com.example.bookzon.infrastructure.repositories.UserRepository;

import java.util.Optional;

public class UserRepositoryGateway implements UserGateway {
    private final UserRepository userRepository;

    public UserRepositoryGateway(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public User createUser(User userDomain) {
        // User userEntity = userEntityMapper.toEntity(userDomain);
        return  userRepository.save(userDomain);
    }

    @Override
    public User findByUsername(String username) {
      var user = (User) userRepository.findByUsername(username);
      return user;
    }


}