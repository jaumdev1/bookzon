package com.example.bookzon.infrastructure.repositories;

import com.example.bookzon.domain.entities.DiscussionTopic;
import com.example.bookzon.infrastructure.dtos.DiscussionTopic.DiscussionTopicWithTop3CommentsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DiscussionTopicRepository extends JpaRepository<DiscussionTopic, UUID> {
    @Query(value = """
            SELECT dt.id AS id, dt.title AS title, dt.description AS description,\s
                   b.id AS book_id, b.title AS book_title,
                   dt.user_id AS user_id, u.username AS creatorUsername,
                   c.id AS commentId, c.content AS commentContent,
                   c.user_id AS comment_user_id, cu.username AS comment_username,\s
                   c.parent_comment_id AS parent_comment_id
            FROM discussion_topics dt
            LEFT JOIN books b ON dt.book_id = b.id
            LEFT JOIN users u ON dt.user_id = u.id
            LEFT JOIN (
                SELECT *,
                       ROW_NUMBER() OVER (PARTITION BY discussion_topic_id ORDER BY id ASC) AS rn
                FROM comments
            ) c ON dt.id = c.discussion_topic_id AND c.rn <= 3
            LEFT JOIN users cu ON c.user_id = cu.id
            WHERE dt.id = :id
            ORDER BY c.id ASC;
            """, nativeQuery = true)
 List<Object[]> findWithTop3CommentsById(@Param("id") UUID id);
}
