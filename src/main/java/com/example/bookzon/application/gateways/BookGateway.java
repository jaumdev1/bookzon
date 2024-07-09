package com.example.bookzon.application.gateways;

import com.example.bookzon.domain.entities.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookGateway {
    Book createBook(Book book);
    List<Book> getAllBooks();

    Book getBookById(UUID id);

    Optional<Book> getBookByGoogleId(String id);
    List<Book> findByTitle(String title);
    Book updateBook(Book book);

    void deleteBook(UUID id);
}
