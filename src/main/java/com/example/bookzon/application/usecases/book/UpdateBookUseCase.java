package com.example.bookzon.application.usecases.book;

import com.example.bookzon.application.gateways.BookGateway;

public class UpdateBookUseCase {

    private final BookGateway bookGateway;

    public UpdateBookUseCase(BookGateway bookGateway){
        this.bookGateway = bookGateway;
    }

}
