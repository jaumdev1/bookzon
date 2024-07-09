package com.example.bookzon.infrastructure.dtos.DiscussionTopic;

import com.example.bookzon.domain.entities.Book;
import com.example.bookzon.domain.entities.DiscussionTopic;
import com.example.bookzon.domain.entities.User;

import java.util.UUID;

public record CreateDiscussionTopicDTO(
        String title,
        String description,
        String googleId

) {
    public DiscussionTopic toEntity() {
        Book book = new Book();

        book.setGoogleId(this.googleId);

        User user = new User();

        return new DiscussionTopic(
                this.title,
                this.description,
                book,
                user
        );
    }
}