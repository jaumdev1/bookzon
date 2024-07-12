package com.example.bookzon.infrastructure.dtos.User;

import java.util.UUID;

public record UserDTO(
        UUID id,
        String username,
        String name,
        String imageUrl


) {}
