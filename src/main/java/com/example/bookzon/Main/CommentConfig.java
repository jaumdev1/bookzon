package com.example.bookzon.Main;

import com.example.bookzon.application.gateways.CommentGateway;

import com.example.bookzon.application.usecases.comment.CreateCommentToDiscussionTopicUseCase;
import com.example.bookzon.application.usecases.comment.CreateCommentToOtherCommentUseCase;
import com.example.bookzon.infrastructure.gateways.CommentRepositoryGateway;
import com.example.bookzon.infrastructure.repositories.CommentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentConfig {

    @Bean
    public CommentGateway  createCommentGateway(CommentRepository  commentRepository){
        return new CommentRepositoryGateway(commentRepository);
    }
    @Bean
    public CreateCommentToDiscussionTopicUseCase createCommentToDiscussionTopicUseCase(CommentGateway commentGateway){
        return new CreateCommentToDiscussionTopicUseCase(commentGateway);
    }
    @Bean
    public CreateCommentToOtherCommentUseCase createCommentToOtherCommentUseCase(CommentGateway commentGateway){
        return new CreateCommentToOtherCommentUseCase(commentGateway);
    }
}
