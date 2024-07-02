package com.example.bookzon.Main;

import com.example.bookzon.application.gateways.BookGateway;
import com.example.bookzon.application.gateways.UserGateway;

import com.example.bookzon.application.usecases.book.SearchBookByTitleUseCase;
import com.example.bookzon.infrastructure.gateways.BookRepositoryGateway;
import com.example.bookzon.infrastructure.gateways.UserRepositoryGateway;
import com.example.bookzon.infrastructure.repositories.BookRepository;
import com.example.bookzon.infrastructure.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConfig {
    @Bean
    public BookGateway bookGateway(BookRepository bookRepository) {
        return new BookRepositoryGateway(bookRepository);
    }
    @Bean
    public SearchBookByTitleUseCase searchBookByTitleUseCase(BookGateway bookGateway) {
        return new SearchBookByTitleUseCase(bookGateway);
    }

}
