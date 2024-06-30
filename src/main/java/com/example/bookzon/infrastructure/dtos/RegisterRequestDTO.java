package com.example.bookzon.infrastructure.dtos;

import com.example.bookzon.domain.enums.UserRole;

public record RegisterRequestDTO(String username, String password) {
}
