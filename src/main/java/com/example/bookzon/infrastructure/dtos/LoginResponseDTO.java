package com.example.bookzon.infrastructure.dtos;

public record LoginResponseDTO(String token, String refreshToken) {
}