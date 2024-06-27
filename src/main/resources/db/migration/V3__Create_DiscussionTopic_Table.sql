CREATE TABLE discussion_topics (
    id UUID PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    book_id UUID,
    user_id UUID,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);