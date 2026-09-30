CREATE TABLE playlist_tracks(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    playlist_id UUID NOT NULL,
    track_id UUID NOT NULL,
    
    position INTEGER NOT NULL DEFAULT 0,
    
    added_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_playlist_track_playlist
        FOREIGN KEY (playlist_id)
        REFERENCES playlists(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_playlist_track_track
        FOREIGN KEY (track_id)
        REFERENCES tracks(id)
        ON DELETE CASCADE
)