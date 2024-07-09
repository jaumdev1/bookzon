package com.example.bookzon.infrastructure.gateways;

import com.example.bookzon.application.gateways.CommentGateway;
import com.example.bookzon.domain.entities.Comment;
import com.example.bookzon.infrastructure.repositories.CommentRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentRepositoryGateway implements CommentGateway {

    private final CommentRepository commentRepository;

    public CommentRepositoryGateway(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }
    public Comment createComment (Comment comment){
       return this.commentRepository.save(comment);
    }

}
