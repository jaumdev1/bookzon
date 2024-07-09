package com.example.bookzon.infrastructure.security;

import com.example.bookzon.application.gateways.AuthenticationGateway;
import com.example.bookzon.application.gateways.TokenService;
import com.example.bookzon.application.gateways.UserGateway;
import com.example.bookzon.domain.entities.User;
import com.example.bookzon.infrastructure.dtos.LoginResponseDTO;
import com.example.bookzon.infrastructure.dtos.RefreshResponseDTO;
import com.example.bookzon.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthenticationService  implements AuthenticationGateway {


    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserGateway userRepository;


    public LoginResponseDTO Login(String username, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
           User user =  userRepository.findByUsername(username);
           var accessToken= tokenService.generateToken(user.getId(), username);
           var refreshToken = tokenService.generateRefreshToken(user.getId(),username);
            return new LoginResponseDTO(accessToken, refreshToken);
        } catch (AuthenticationException e) {
            throw e;
        }
    }

    public RefreshResponseDTO Refresh(String refreshToken){
        try {
            String username = tokenService.getUsernameFromToken(refreshToken);
            User user =  userRepository.findByUsername(username);
            String accessToken = tokenService.generateToken(user.getId(),username);
            String newRefreshToken = tokenService.generateRefreshToken(user.getId(),username);
            return new RefreshResponseDTO(accessToken, newRefreshToken);
        } catch (AuthenticationException e) {
            throw e;
        }


    }

    @Override
    public Authentication authenticate(String username, String password) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
    }
}
