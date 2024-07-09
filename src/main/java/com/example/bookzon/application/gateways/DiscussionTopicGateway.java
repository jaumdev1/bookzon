package com.example.bookzon.application.gateways;

import com.example.bookzon.domain.entities.Book;
import com.example.bookzon.domain.entities.DiscussionTopic;
import com.example.bookzon.infrastructure.dtos.DiscussionTopic.DiscussionTopicWithTop3CommentsDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DiscussionTopicGateway {
    DiscussionTopic createDiscussionTopic(DiscussionTopic discussionTopic);
    List<DiscussionTopic> getAllDiscussionTopics();

    DiscussionTopic getDiscussionTopicById(UUID id);

    Optional<DiscussionTopicWithTop3CommentsDTO> getDiscussionTopicWithCTop3CommentById(UUID id);
    DiscussionTopic updateDiscussionTopic(DiscussionTopic discussionTopic);

    void deleteDiscussionTopic(UUID id);
}
