package com.example.bookzon.domain.entities;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "discussion_topics")
public class DiscussionTopic {
    @Id
    @GeneratedValue(generator = "UUID")

    private UUID id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "discussionTopic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
}