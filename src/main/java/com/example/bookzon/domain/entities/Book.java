package com.example.bookzon.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String title;
    private String author;
    private int yearOfPublication;
    private String description;
    private String genre;
    private String coverImage;
}