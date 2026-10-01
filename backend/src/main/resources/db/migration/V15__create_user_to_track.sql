create table users_tracks(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    user_id UUID NOT NULL,
    track_id UUID NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_user_track_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_user_track_track
        FOREIGN KEY (track_id)
        REFERENCES tracks(id)
        ON DELETE CASCADE,

    CONSTRAINT unique_user_track UNIQUE (user_id, track_id)
)