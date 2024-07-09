package com.example.bookzon.application.gateways;

import com.example.bookzon.infrastructure.dtos.LoginResponseDTO;
import com.example.bookzon.infrastructure.dtos.RefreshResponseDTO;
import org.springframework.security.core.Authentication;

public interface AuthenticationGateway {

     LoginResponseDTO Login(String username, String password);

     RefreshResponseDTO Refresh(String refreshToken);

     Authentication authenticate(String username, String password);
}
