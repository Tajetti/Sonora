package br.com.tajetti.backend.relations;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import br.com.tajetti.backend.artist.Artist;
import br.com.tajetti.backend.relations.enums.TrackArtistEnum;
import br.com.tajetti.backend.track.Track;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity 
@Table(name = "track_artists")
public class TrackArtist {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn (name = "track_id", nullable = false)
    private Track track;
    
    @ManyToOne(optional = false)
    @JoinColumn (name = "artist_id", nullable = false)
    private Artist artist;

    @NotNull 
    @Enumerated (EnumType.STRING)
    private TrackArtistEnum role = TrackArtistEnum.PRIMARY;

    private int position = 0;

    @CreationTimestamp
    @Column (name = "created_at", updatable = false)
    private OffsetDateTime createdAt;
}
