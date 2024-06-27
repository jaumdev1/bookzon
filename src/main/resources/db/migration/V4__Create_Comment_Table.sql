CREATE TABLE comments (
    id UUID PRIMARY KEY,
    content TEXT NOT NULL,
    discussion_topic_id UUID,
    user_id UUID,
    parent_comment_id UUID,
    FOREIGN KEY (discussion_topic_id) REFERENCES discussion_topics(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (parent_comment_id) REFERENCES comments(id)
);
