package com.example.bookzon.application.usecases.comment;
import com.example.bookzon.application.gateways.CommentGateway;
import com.example.bookzon.domain.entities.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
class CreateCommentToOtherCommentUseCaseTest {

    @Mock
    private CommentGateway commentGateway;

    @InjectMocks
    private CreateCommentToOtherCommentUseCase createCommentToOtherCommentUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteSuccess() {

        Comment comment = mock(Comment.class);
        Comment savedComment = mock(Comment.class);

        doNothing().when(comment).validateToOtherComment();
        when(commentGateway.createComment(comment)).thenReturn(savedComment);


        Comment result = createCommentToOtherCommentUseCase.execute(comment);

        assertEquals(savedComment, result);
        verify(comment).validateToOtherComment();
        verify(commentGateway).createComment(comment);
    }

    @Test
    void testExecuteValidationFails() {
        Comment comment = mock(Comment.class);

        doThrow(new IllegalArgumentException("DiscussionTopic and ParentComment must be defined."))
                .when(comment).validateToOtherComment();


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            createCommentToOtherCommentUseCase.execute(comment);
        });

        assertEquals("DiscussionTopic and ParentComment must be defined.", exception.getMessage());
        verify(comment).validateToOtherComment();
        verifyNoInteractions(commentGateway);
    }
}