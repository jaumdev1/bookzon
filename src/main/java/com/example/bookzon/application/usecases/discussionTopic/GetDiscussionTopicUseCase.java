package com.example.bookzon.application.usecases.discussionTopic;

import com.example.bookzon.application.gateways.DiscussionTopicGateway;
import com.example.bookzon.domain.entities.DiscussionTopic;
import com.example.bookzon.infrastructure.dtos.DiscussionTopic.DiscussionTopicWithTop3CommentsDTO;
import com.example.bookzon.infrastructure.googleapi.GoogleBooksService;
import com.example.bookzon.infrastructure.repositories.DiscussionTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public class GetDiscussionTopicUseCase {

    private final DiscussionTopicGateway discussionTopicGateway;

    public GetDiscussionTopicUseCase(DiscussionTopicGateway discussionTopicGateway){
        this.discussionTopicGateway = discussionTopicGateway;
    }
    public Optional<DiscussionTopicWithTop3CommentsDTO> execute(UUID id) {
        return discussionTopicGateway.getDiscussionTopicWithCTop3CommentById(id);
    }
}
