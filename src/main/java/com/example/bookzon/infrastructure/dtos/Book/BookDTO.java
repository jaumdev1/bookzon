package com.example.bookzon.infrastructure.dtos.Book;

import java.util.UUID;

public record BookDTO(
        String id,
        String title,
        String author,
        int yearOfPublication,
        String description,
        String genre,
        String coverImage
) {}