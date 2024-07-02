ALTER TABLE books
ADD COLUMN google_id VARCHAR(255);

ALTER TABLE books
ADD CONSTRAINT uk_google_id UNIQUE (google_id);