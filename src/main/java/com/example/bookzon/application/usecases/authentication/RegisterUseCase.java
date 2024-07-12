package com.example.bookzon.application.usecases.authentication;

import com.example.bookzon.application.gateways.UserGateway;
import com.example.bookzon.domain.entities.User;
import com.example.bookzon.domain.enums.AuthProvider;
import com.example.bookzon.domain.exceptions.InvalidUserDataException;
import com.example.bookzon.domain.exceptions.UserAlreadyExistsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class RegisterUseCase {
    private final UserGateway userGateway;

    public RegisterUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User execute(User user){
        if (userGateway.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("User already exists with login: " + user.getUsername());
        }

        if (user.getUsername() == null || user.getPassword() == null) {
            throw new InvalidUserDataException("User data is invalid");
        }
        user.setProvider(AuthProvider.local);

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);

        return userGateway.createUser(user);
    }
}
