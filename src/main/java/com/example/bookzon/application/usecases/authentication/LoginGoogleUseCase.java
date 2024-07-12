package com.example.bookzon.application.usecases.authentication;

import com.example.bookzon.application.gateways.UserGateway;
import com.example.bookzon.domain.entities.User;

import java.util.Optional;

public class LoginGoogleUseCase {

    private final UserGateway userGateway;

    public LoginGoogleUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User execute(String googleId, String name, String email){


        User user = new User() ;
  /*      if (existingUser.isPresent()) {
            user = existingUser.get();
            user.setName(name);
            user.setEmail(email);
        } else {
            user = new User();
            user.setName(name);
            user.setEmail(email);
        }
        return userGateway.createUser(user);*/

        return user;
    }
}
