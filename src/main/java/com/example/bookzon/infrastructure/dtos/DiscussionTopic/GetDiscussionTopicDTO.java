package com.example.bookzon.infrastructure.dtos.DiscussionTopic;

import com.example.bookzon.domain.entities.Book;
import com.example.bookzon.domain.entities.DiscussionTopic;
import com.example.bookzon.domain.entities.User;
import com.example.bookzon.infrastructure.dtos.Comment.CommentDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record GetDiscussionTopicDTO(
        UUID id,
        String title,
        String description,
        UUID bookId,
        String bookTitle,
        UUID userId,
        String username,
        List<CommentDTO> comments

) {
    public DiscussionTopic toEntity() {

        Book book = new Book();
        book.setId(this.bookId);

        User user = new User();
        user.setId(this.userId);

        return new DiscussionTopic(
                this.title,
                this.description,
                book,
                user

        );
    }

    public static GetDiscussionTopicDTO fromEntity(DiscussionTopic topic) {
        return new GetDiscussionTopicDTO(
                topic.getId(),
                topic.getTitle(),
                topic.getDescription(),
                topic.getBook().getId(),
                topic.getBook().getTitle(),
                topic.getUser().getId(),
                topic.getUser().getUsername(),
                topic.getComments().stream()
                        .map(CommentDTO::fromEntity)
                        .collect(Collectors.toList())
        );
    }
}
