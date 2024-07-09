package com.example.bookzon.infrastructure.security.UserIdStrategy;

import java.util.UUID;

public interface UserIdStrategy {
    UUID getCurrentUserId();
}
