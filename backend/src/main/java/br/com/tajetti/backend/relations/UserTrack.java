package br.com.tajetti.backend.relations;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.tajetti.backend.track.Track;
import br.com.tajetti.backend.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "users_tracks")
public class UserTrack {
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    
    @ManyToOne(optional = false)
    @JoinColumn (name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne(optional = false)
    @JoinColumn (name = "track_id", nullable = false)
    private Track track;

    @CreationTimestamp 
    @Column (name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp 
    @Column (name = "updated_at")
    private OffsetDateTime updatedAt;
}
