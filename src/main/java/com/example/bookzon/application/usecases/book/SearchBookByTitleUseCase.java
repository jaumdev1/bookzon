package com.example.bookzon.application.usecases.book;

import com.example.bookzon.application.gateways.BookGateway;
import com.example.bookzon.domain.entities.Book;
import com.example.bookzon.infrastructure.dtos.Book.BookDTO;
import com.example.bookzon.infrastructure.dtos.Book.ResponseSearchBookDTO;
import com.example.bookzon.infrastructure.googleapi.GoogleBooksService;
import com.google.api.services.books.v1.model.Volumes;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SearchBookByTitleUseCase {

    private final BookGateway bookGateway;
    @Autowired
    private GoogleBooksService googleBooksService;
    public SearchBookByTitleUseCase(BookGateway bookGateway){
        this.bookGateway = bookGateway;
    }

    public ResponseSearchBookDTO execute(String titleRequest) throws IOException {
        List<BookDTO> books = googleBooksService.searchBooks(titleRequest);
        return new ResponseSearchBookDTO(books);
    }

}
