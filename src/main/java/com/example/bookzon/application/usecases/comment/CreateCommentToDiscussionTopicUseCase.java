package com.example.bookzon.application.usecases.comment;

import com.example.bookzon.application.gateways.CommentGateway;
import com.example.bookzon.application.usecases.discussionTopic.CreateDiscussionTopicUseCase;
import com.example.bookzon.domain.entities.Comment;
import io.swagger.v3.oas.annotations.tags.Tag;


public class CreateCommentToDiscussionTopicUseCase {
    private final CommentGateway commentGateway;
    public CreateCommentToDiscussionTopicUseCase(CommentGateway commentGateway){
        this.commentGateway = commentGateway;
    }
    public Comment  execute(Comment comment){
        comment.validateToDiscussionTopic();
        return this.commentGateway.createComment(comment);
    }
}
