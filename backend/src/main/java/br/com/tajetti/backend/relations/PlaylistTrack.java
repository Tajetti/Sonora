package br.com.tajetti.backend.relations;

import java.util.UUID;
import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;

import br.com.tajetti.backend.playlist.Playlist;
import br.com.tajetti.backend.track.Track;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "playlist_tracks")
public class PlaylistTrack {
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne (optional = false)
    @JoinColumn (name = "playlist_id", nullable = false)
    private Playlist playlist;

    @ManyToOne (optional = false)
    @JoinColumn (name = "track_id", nullable = false)
    private Track track;
    
    private int position = 0;

    @CreationTimestamp
    @Column (name = "added_at", updatable = false)
    private OffsetDateTime addedAt;

}
