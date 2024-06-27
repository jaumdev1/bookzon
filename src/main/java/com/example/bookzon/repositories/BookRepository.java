package com.example.bookzon.repositories;

import com.example.bookzon.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository  extends JpaRepository<Book, UUID> {
}
