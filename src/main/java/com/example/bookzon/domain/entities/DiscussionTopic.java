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
    public  DiscussionTopic( String title, String description, Book book, User user, List<Comment> collect) {
        this.title = title;
        this.description = description;
        this.book = book;
        this.user = user;
        this.comments = collect;
    }
    public DiscussionTopic(String title, String description,  Book book, User user){
        this.title = title;
        this.description = description;
        this.book = book;
        this.user = user;
    }
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "discussionTopic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

}