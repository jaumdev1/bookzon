 package com.example.bookzon.infrastructure.gateways;

import com.example.bookzon.application.gateways.BookGateway;
import com.example.bookzon.domain.entities.Book;
import com.example.bookzon.infrastructure.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookRepositoryGateway implements BookGateway {

    private final BookRepository bookRepository;

    public BookRepositoryGateway(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(UUID id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.orElse(null);
    }

    @Override
    public Optional<Book> getBookByGoogleId(String googleId) {
        return bookRepository.findByGoogleId(googleId);
    }

    @Override
    public Book updateBook(Book book) {
        bookRepository.findById(book.getId()).orElseThrow(() ->
        new RuntimeException("Book with ID " + book.getId() + " not found for update"));
        return bookRepository.save(book);
    };


    @Override
    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
}

