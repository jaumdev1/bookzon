package com.example.bookzon.application.usecases.comment;

import com.example.bookzon.application.gateways.CommentGateway;
import com.example.bookzon.domain.entities.Comment;

public class CreateCommentToOtherCommentUseCase {

    private final CommentGateway commentGateway;
    public CreateCommentToOtherCommentUseCase(CommentGateway commentGateway){
        this.commentGateway = commentGateway;
    }

    public Comment  execute(Comment comment){
        comment.validateToOtherComment();
        return this.commentGateway.createComment(comment);
    }
}
