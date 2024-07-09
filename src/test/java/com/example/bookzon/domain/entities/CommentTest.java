package com.example.bookzon.domain.entities;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

class CommentTest {

    private User user;
    private DiscussionTopic discussionTopic;
    private Comment parentComment;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername("Test User");

        discussionTopic = new DiscussionTopic();
        discussionTopic.setId(UUID.randomUUID());
        discussionTopic.setTitle("Test Discussion Topic");

        parentComment = new Comment();
        parentComment.setId(UUID.randomUUID());
        parentComment.setContent("Parent Comment");
    }

    @Test
    void testCreateCommentWithDiscussionTopic() {
        Comment comment = new Comment("This is a comment", discussionTopic, user);
        comment.validateToDiscussionTopic();
        assertEquals("This is a comment", comment.getContent());
        assertEquals(discussionTopic, comment.getDiscussionTopic());
        assertEquals(user, comment.getUser());
        assertNull(comment.getParentComment());
    }

    @Test
    void testCreateCommentWithParentComment() {
        Comment comment = new Comment("This is a reply", null, user, parentComment);
        comment.validateToOtherComment();
        assertEquals("This is a reply", comment.getContent());
        assertNull(comment.getDiscussionTopic());
        assertEquals(user, comment.getUser());
        assertEquals(parentComment, comment.getParentComment());
    }

    @Test
    void testInvalidCommentWithBothDiscussionTopicAndParentComment() {
        Comment comment = new Comment("Invalid comment", discussionTopic, user, parentComment);
        Exception exception = assertThrows(IllegalArgumentException.class, comment::validateToDiscussionTopic);
        assertEquals("ParentComment must be null for a top-level comment.", exception.getMessage());
    }

    @Test
    void testInvalidCommentWithoutDiscussionTopic() {
        Comment comment = new Comment("Invalid comment", null, user);
        Exception exception = assertThrows(IllegalArgumentException.class, comment::validateToDiscussionTopic);
        assertEquals("DiscussionTopic must be defined.", exception.getMessage());
    }

    @Test
    void testInvalidCommentWithoutParentComment() {
        Comment comment = new Comment("Invalid reply", null, user, null);
        Exception exception = assertThrows(IllegalArgumentException.class, comment::validateToOtherComment);
        assertEquals("Both discussionTopic and parentComment must be set at the same time for a reply.", exception.getMessage());
    }
}

