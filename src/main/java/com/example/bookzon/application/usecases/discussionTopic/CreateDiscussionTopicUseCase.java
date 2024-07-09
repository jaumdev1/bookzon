package com.example.bookzon.application.usecases.discussionTopic;

import com.example.bookzon.application.gateways.BookGateway;
import com.example.bookzon.application.gateways.DiscussionTopicGateway;
import com.example.bookzon.domain.entities.Book;
import com.example.bookzon.domain.entities.DiscussionTopic;
import com.example.bookzon.infrastructure.dtos.Book.BookDTO;
import com.example.bookzon.infrastructure.googleapi.GoogleBooksService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class CreateDiscussionTopicUseCase {
    private final DiscussionTopicGateway discussionTopicGateway;
    private final BookGateway bookGateway;
    @Autowired
    private GoogleBooksService googleBooksService;
    public CreateDiscussionTopicUseCase( DiscussionTopicGateway discussionTopicGateway, BookGateway bookGateway){
        this.discussionTopicGateway = discussionTopicGateway;
        this.bookGateway = bookGateway;
    }
    public DiscussionTopic execute(DiscussionTopic discussionTopic){

     var book  =  bookGateway.getBookByGoogleId(discussionTopic.getBook().getGoogleId());

     var bookResult = book.orElseGet(() -> {
         Book newBook = null;
         try {
          newBook = googleBooksService.findBookById(discussionTopic.getBook().getGoogleId());
          return bookGateway.createBook(newBook);
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
     }

    );
    discussionTopic.setBook(bookResult);

  return discussionTopicGateway.createDiscussionTopic(discussionTopic);
    }
}
