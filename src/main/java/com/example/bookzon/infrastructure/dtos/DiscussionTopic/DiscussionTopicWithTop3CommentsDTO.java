package com.example.bookzon.infrastructure.dtos.DiscussionTopic;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


public class DiscussionTopicWithTop3CommentsDTO {
    private UUID id;
    private String title;
    private String description;
    private UUID bookId;
    private String book_title;
    private UUID userId;
    private String creator_username;
    private List<CommentDTO> top_comments;

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String get_title() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return book_title;
    }

    public void setBookTitle(String bookTitle) {
        this.book_title = bookTitle;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getCreatorUsername() {
        return creator_username;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creator_username = creatorUsername;
    }

    public List<CommentDTO> getTopComments() {
        return top_comments;
    }

    public void setTopComments(List<CommentDTO> topComments) {
        this.top_comments = topComments;
    }

    public static class CommentDTO {
        private UUID commentId;
        private String commentContent;
        private UUID commentUserId;
        private String commentUsername;
        private UUID parentCommentId;

        // Getters e Setters
        public UUID getCommentId() {
            return commentId;
        }

        public void setCommentId(UUID commentId) {
            this.commentId = commentId;
        }

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public UUID getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(UUID commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUsername() {
            return commentUsername;
        }

        public void setCommentUsername(String commentUsername) {
            this.commentUsername = commentUsername;
        }

        public UUID getParentCommentId() {
            return parentCommentId;
        }

        public void setParentCommentId(UUID parentCommentId) {
            this.parentCommentId = parentCommentId;
        }
    }
}