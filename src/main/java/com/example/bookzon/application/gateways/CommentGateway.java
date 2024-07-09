package com.example.bookzon.application.gateways;

import com.example.bookzon.domain.entities.Comment;

public interface CommentGateway {

    Comment createComment(Comment comment);
}
