package com.example.bookzon.application.usecases.discussionTopic;

import com.example.bookzon.application.gateways.DiscussionTopicGateway;
import com.example.bookzon.domain.entities.DiscussionTopic;
import com.example.bookzon.infrastructure.repositories.DiscussionTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public class GetDiscussionTopicPagedByUserIdUseCase {

    public final DiscussionTopicGateway discussionTopicGateway;

    @Autowired
    private DiscussionTopicRepository discussionTopicRepository;

    public GetDiscussionTopicPagedByUserIdUseCase(DiscussionTopicGateway discussionTopicGateway){
        this.discussionTopicGateway = discussionTopicGateway;
    }

    public Page<DiscussionTopic> execute(Pageable page, UUID userId) {
        return discussionTopicRepository.findAll(page);

    }
}
