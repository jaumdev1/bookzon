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
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "discussion_topic_id")
    private DiscussionTopic discussionTopic;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> replies;


    public Comment(String content, DiscussionTopic discussionTopic, User user, Comment parentComment) {
        this.content = content;
        this.discussionTopic = discussionTopic;
        this.user = user;
        this.parentComment = parentComment;
    }
    public Comment(String content, DiscussionTopic discussionTopic, User user) {
        this.content = content;
        this.discussionTopic = discussionTopic;
        this.user = user;
    }
    public void validateToDiscussionTopic() {
        if (discussionTopic == null) {
            throw new IllegalArgumentException("DiscussionTopic must be defined.");
        }
        if (parentComment != null) {
            throw new IllegalArgumentException("ParentComment must be null for a top-level comment.");
        }
    }

    public void validateToOtherComment() {
        if (parentComment == null && discussionTopic == null) {
            throw new IllegalArgumentException("Both discussionTopic and parentComment must be set at the same time for a reply.");
        }
    }
}