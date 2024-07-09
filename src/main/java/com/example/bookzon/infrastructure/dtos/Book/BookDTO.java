package com.example.bookzon.infrastructure.dtos.Book;

import com.example.bookzon.domain.entities.Book;

import java.util.UUID;

public record BookDTO(
        String googleId,
        String title,
        String author,
        int yearOfPublication,
        String description,
        String genre,
        String coverImage
) {

    public Book toEntity() {
        return new Book(
                googleId,
                title,
                author,
                yearOfPublication,
                description,
                genre,
                coverImage
        );
    }
}