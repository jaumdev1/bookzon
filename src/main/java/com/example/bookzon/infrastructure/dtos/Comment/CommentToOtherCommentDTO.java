package com.example.bookzon.infrastructure.dtos.Comment;

import com.example.bookzon.domain.entities.Comment;
import com.example.bookzon.domain.entities.DiscussionTopic;
import com.example.bookzon.domain.entities.User;

import java.util.UUID;

public record CommentToOtherCommentDTO( String content,
                                        UUID discussionTopicId,
                                        UUID parentCommentId) {

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
        Comment comment = null;
        if (parentCommentId != null) {
            comment = new Comment();
            comment.setId(parentCommentId);
        }
        return new Comment(
                this.content,
                discussionTopic,
                user,
                comment
        );
    }
}
