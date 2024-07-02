package com.example.bookzon.application.usecases.book;

import com.example.bookzon.application.gateways.BookGateway;

public class GetBookByIdUseCase {

    private final BookGateway bookGateway;

    public GetBookByIdUseCase(BookGateway bookGateway){
        this.bookGateway = bookGateway;
    }

}
