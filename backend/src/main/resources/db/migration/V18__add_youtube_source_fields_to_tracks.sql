ALTER TABLE tracks
    ADD COLUMN source_id VARCHAR(255),
    ADD COLUMN source_url TEXT,
    ADD COLUMN thumbnail_url TEXT,
    ADD COLUMN channel_name VARCHAR(255),
    ADD COLUMN uploader_name VARCHAR(255);