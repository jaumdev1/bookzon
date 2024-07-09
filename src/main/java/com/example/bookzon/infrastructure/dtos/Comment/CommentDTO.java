package com.example.bookzon.infrastructure.dtos.Comment;

import com.example.bookzon.domain.entities.Comment;
import com.example.bookzon.domain.entities.DiscussionTopic;
import com.example.bookzon.domain.entities.User;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record CommentDTO(
        String content,
        UUID discussionTopicId,
        UUID userId,
        UUID parentCommentId
) {

    public Comment toEntity() {

        DiscussionTopic discussionTopic = null;
        if (this.discussionTopicId != null) {
            discussionTopic = new DiscussionTopic();
            discussionTopic.setId(this.discussionTopicId);
        }

        User user = null;
        if (this.userId != null) {
            user = new User();
            user.setId(this.userId);
        }

        Comment parentComment = null;
        if (this.parentCommentId != null) {
            parentComment = new Comment();
            parentComment.setId(this.parentCommentId);
        }

        return new Comment(
                this.content,
                discussionTopic,
                user,
                parentComment
        );
    }

    public static List<CommentDTO> fromEntities(List<Comment> comments) {
        return comments.stream()
                .map(CommentDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public static CommentDTO fromEntity(Comment comment) {
        UUID discussionTopicId = null;
        if (comment.getDiscussionTopic() != null) {
            discussionTopicId = comment.getDiscussionTopic().getId();
        }

        UUID userId = null;
        if (comment.getUser() != null) {
            userId = comment.getUser().getId();
        }

        UUID parentCommentId = null;
        if (comment.getParentComment() != null) {
            parentCommentId = comment.getParentComment().getId();
        }

        return new CommentDTO(
                comment.getContent(),
                discussionTopicId,
                userId,
                parentCommentId
        );
    }
}