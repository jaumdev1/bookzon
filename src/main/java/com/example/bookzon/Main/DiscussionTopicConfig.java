package com.example.bookzon.Main;

import com.example.bookzon.application.gateways.BookGateway;
import com.example.bookzon.application.gateways.DiscussionTopicGateway;
import com.example.bookzon.application.usecases.book.SearchBookByTitleUseCase;
import com.example.bookzon.application.usecases.discussionTopic.CreateDiscussionTopicUseCase;
import com.example.bookzon.application.usecases.discussionTopic.GetDiscussionTopicPagedUseCase;
import com.example.bookzon.application.usecases.discussionTopic.GetDiscussionTopicUseCase;
import com.example.bookzon.infrastructure.gateways.BookRepositoryGateway;
import com.example.bookzon.infrastructure.gateways.DiscussionTopicRepositoryGateway;
import com.example.bookzon.infrastructure.repositories.BookRepository;
import com.example.bookzon.infrastructure.repositories.DiscussionTopicRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscussionTopicConfig {

    @Bean
    public DiscussionTopicGateway discussionTopicGateway(DiscussionTopicRepository discussionTopicRepository) {
        return new DiscussionTopicRepositoryGateway(discussionTopicRepository);
    }
    @Bean
    public CreateDiscussionTopicUseCase createDiscussionTopicUseCase(DiscussionTopicGateway discussionTopicGateway, BookGateway bookGateway) {
        return new CreateDiscussionTopicUseCase(discussionTopicGateway, bookGateway);
    }
    @Bean
    public GetDiscussionTopicPagedUseCase getDiscussionTopicPagedUseCase(DiscussionTopicGateway discussionTopicGateway) {
        return new GetDiscussionTopicPagedUseCase(discussionTopicGateway);
    }
    @Bean
    public GetDiscussionTopicUseCase getDiscussionTopicUseCase (DiscussionTopicGateway discussionTopicGateway){
        return  new GetDiscussionTopicUseCase(discussionTopicGateway);
    }

}
