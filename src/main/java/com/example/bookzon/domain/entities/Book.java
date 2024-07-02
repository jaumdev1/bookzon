package com.example.bookzon.domain.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import java.util.UUID;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String googleId;

    private String title;
    private String author;
    private int yearOfPublication;
    private String description;
    private String genre;
    private String coverImage;
}