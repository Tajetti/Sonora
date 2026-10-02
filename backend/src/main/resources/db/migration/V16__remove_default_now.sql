ALTER TABLE tracks 
    ALTER COLUMN created_at 
    DROP DEFAULT;

ALTER TABLE tracks
    ALTER COLUMN updated_at 
    DROP DEFAULT;