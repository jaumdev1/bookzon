package com.example.bookzon.infrastructure.repositories;

import com.example.bookzon.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository  extends JpaRepository<Book, UUID> {
    List<Book> findByTitleContainingIgnoreCase(String title);
}
