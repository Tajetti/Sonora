CREATE TABLE track_artists (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    track_id UUID NOT NULL,
    artist_id UUID NOT NULL,

    role VARCHAR(30) NOT NULL DEFAULT 'PRIMARY', 
    position INTEGER NOT NULL DEFAULT 0,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_track_artists_track
        FOREIGN KEY (track_id)
        REFERENCES tracks(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_track_artists_artist
        FOREIGN KEY (artist_id)
        REFERENCES artists(id)
        ON DELETE CASCADE,

    CONSTRAINT uq_track_artists
        UNIQUE (track_id, artist_id)
);