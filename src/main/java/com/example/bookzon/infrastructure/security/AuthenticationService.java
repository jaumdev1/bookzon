package com.example.bookzon.infrastructure.security;

import com.example.bookzon.application.gateways.AuthenticationGateway;
import com.example.bookzon.application.gateways.TokenService;
import com.example.bookzon.domain.entities.User;
import com.example.bookzon.infrastructure.dtos.LoginResponseDTO;
import com.example.bookzon.infrastructure.dtos.RefreshResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService  implements AuthenticationGateway {


    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;



    public LoginResponseDTO Login(String username, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
           var accessToken= tokenService.generateToken(username);
           var refreshToken = tokenService.generateRefreshToken(username);
            return new LoginResponseDTO(accessToken, refreshToken);
        } catch (AuthenticationException e) {
            throw e;
        }


    }
    public RefreshResponseDTO Refresh(String refreshToken){
        try {
            String username = tokenService.getUsernameFromToken(refreshToken);

            String accessToken = tokenService.generateToken(username);
            String newRefreshToken = tokenService.generateRefreshToken(username);
            return new RefreshResponseDTO(accessToken, newRefreshToken);
        } catch (AuthenticationException e) {
            throw e;
        }


    }
}
