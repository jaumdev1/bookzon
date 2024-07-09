ALTER TABLE books
ADD CONSTRAINT uk_google_id UNIQUE (google_id);