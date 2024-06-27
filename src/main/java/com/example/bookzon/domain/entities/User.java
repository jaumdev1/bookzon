package com.example.bookzon.domain.entities;

import jakarta.persistence.*;
import lombok.*;


import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")

    private UUID id;

    private String username;
    private String email;
    private String password;
}