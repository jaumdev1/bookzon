package com.example.bookzon.infrastructure.controllers;

import com.example.bookzon.application.usecases.comment.CreateCommentToDiscussionTopicUseCase;
import com.example.bookzon.application.usecases.comment.CreateCommentToOtherCommentUseCase;
import com.example.bookzon.application.usecases.discussionTopic.CreateDiscussionTopicUseCase;
import com.example.bookzon.domain.entities.Comment;
import com.example.bookzon.domain.entities.DiscussionTopic;
import com.example.bookzon.domain.entities.User;
import com.example.bookzon.infrastructure.dtos.Comment.CommentDTO;
import com.example.bookzon.infrastructure.dtos.Comment.CommentToDiscussionTopicDTO;
import com.example.bookzon.infrastructure.dtos.Comment.CommentToOtherCommentDTO;
import com.example.bookzon.infrastructure.dtos.DiscussionTopic.GetDiscussionTopicDTO;
import com.example.bookzon.infrastructure.security.UserIdStrategy.Factory.UserIdStrategyFactory;
import com.example.bookzon.infrastructure.utils.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Tag(name = "Comment", description= "*")
public class CommentController {

    private final CreateCommentToDiscussionTopicUseCase createCommentToDiscussionTopicUseCase;
    private final CreateCommentToOtherCommentUseCase createCommentToOtherCommentUseCase;

    @Autowired
    private UserIdStrategyFactory userIdStrategyFactory;

    public CommentController(CreateCommentToDiscussionTopicUseCase createCommentToDiscussionTopicUseCase, CreateCommentToOtherCommentUseCase createCommentToOtherCommentUseCase ){
    this.createCommentToDiscussionTopicUseCase = createCommentToDiscussionTopicUseCase;
    this.createCommentToOtherCommentUseCase = createCommentToOtherCommentUseCase;
    }

    @PostMapping("/")
    public ResponseEntity createCommentToDiscussionTopic(@RequestBody CommentToDiscussionTopicDTO commentToDiscussionTopicDTO, HttpSession session){
    UUID userId = userIdStrategyFactory.getStrategy(session).getCurrentUserId();
    Comment entity  = commentToDiscussionTopicDTO.toEntity(userId);
    Comment result = createCommentToDiscussionTopicUseCase.execute(entity);
    return ResponseEntity.ok(CommentDTO.fromEntity(result));
    }
    @PostMapping("/commentToComment")
    public ResponseEntity<CommentDTO> createCommentToOtherComment(@RequestBody CommentToOtherCommentDTO commentToOtherCommentDTO, HttpSession session){
        UUID userId = userIdStrategyFactory.getStrategy(session).getCurrentUserId();
        Comment entity  = commentToOtherCommentDTO.toEntity(userId);
        var result = createCommentToOtherCommentUseCase.execute(entity);
        return ResponseEntity.ok(  CommentDTO.fromEntity(result));
    }





}
