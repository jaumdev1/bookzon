package com.example.bookzon.application.gateways;

import com.example.bookzon.infrastructure.dtos.LoginResponseDTO;
import com.example.bookzon.infrastructure.dtos.RefreshResponseDTO;

public interface AuthenticationGateway {

     LoginResponseDTO Login(String username, String password);

    RefreshResponseDTO Refresh(String refreshToken);
}
