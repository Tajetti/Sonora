alter table playlists
    add column user_id UUID not null;

alter table playlists
    add constraint fk_playlist_user foreign key (user_id) references "users"(id) on delete cascade;