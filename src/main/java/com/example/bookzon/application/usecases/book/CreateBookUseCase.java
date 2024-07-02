package com.example.bookzon.application.usecases.book;

import com.example.bookzon.application.gateways.AuthenticationGateway;
import com.example.bookzon.application.gateways.BookGateway;
import com.example.bookzon.domain.entities.Book;

public class CreateBookUseCase {

    private final BookGateway bookGateway;

    public CreateBookUseCase(BookGateway bookGateway){
        this.bookGateway = bookGateway;
    }

    public void execute(Book book){
        bookGateway.createBook(book);
    }

}
