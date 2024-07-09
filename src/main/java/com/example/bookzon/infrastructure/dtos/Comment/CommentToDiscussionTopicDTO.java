package com.example.bookzon.infrastructure.dtos.Comment;

import com.example.bookzon.domain.entities.Comment;
import com.example.bookzon.domain.entities.DiscussionTopic;
import com.example.bookzon.domain.entities.User;

import java.util.UUID;

public record CommentToDiscussionTopicDTO(String content,
                                          UUID discussionTopicId) {


    public Comment toEntity(UUID userId) {

        DiscussionTopic discussionTopic = null;
        if (this.discussionTopicId != null) {
            discussionTopic = new DiscussionTopic();
            discussionTopic.setId(this.discussionTopicId);
        }

        User user = null;
        if (userId != null) {
            user = new User();
            user.setId(userId);
        }

        return new Comment(
                this.content,
                discussionTopic,
                user
        );
    }

}
