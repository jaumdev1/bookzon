package com.example.bookzon.application.usecases.book;

import com.example.bookzon.application.gateways.BookGateway;

public class DeleteBookUseCase {

    private final BookGateway bookGateway;

    public DeleteBookUseCase(BookGateway bookGateway){
        this.bookGateway = bookGateway;
    }

}
