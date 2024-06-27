CREATE TABLE books (
    id UUID PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    year_of_publication INT NOT NULL,
    description TEXT,
    genre VARCHAR(50),
    cover_image VARCHAR(255)
);