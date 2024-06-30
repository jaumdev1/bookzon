package com.example.bookzon.Main;

import com.example.bookzon.application.gateways.AuthenticationGateway;
import com.example.bookzon.application.gateways.UserGateway;
import com.example.bookzon.application.usecases.authentication.LoginUseCase;
import com.example.bookzon.application.usecases.authentication.RefreshUseCase;
import com.example.bookzon.application.usecases.authentication.RegisterUseCase;
import com.example.bookzon.infrastructure.gateways.UserRepositoryGateway;
import com.example.bookzon.infrastructure.repositories.UserRepository;
import com.example.bookzon.infrastructure.security.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class UserConfig {



    @Bean
    public RegisterUseCase registerUseCase(UserGateway userGateway) {
        return new RegisterUseCase(userGateway);
    }

    @Bean
    public UserGateway userGateway(UserRepository userRepository) {
        return new UserRepositoryGateway(userRepository);
    }

     @Bean
    AuthenticationGateway authenticationGateway(){

        return new AuthenticationService();
    }

    @Bean
    public LoginUseCase loginUseCase(AuthenticationGateway authenticationGateway) {
        return new LoginUseCase(authenticationGateway);
    }
    @Bean
    public RefreshUseCase refreshUseCase(AuthenticationGateway authenticationGateway) {
        return new RefreshUseCase(authenticationGateway);
    }

}