package br.com.tajetti.backend.playlist;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table (name = "playlists")
public class Playlist {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    @CreationTimestamp
    @Column (name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp 
    @Column (name = "updated_at")
    private OffsetDateTime updatedAt;
}
