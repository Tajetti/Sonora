package br.com.tajetti.backend.artist;

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
@Table (name = "artists")
public class Artist {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String name;

    @Column (name = "image_url")
    private String imageUrl;

    @CreationTimestamp 
    @Column (name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp 
    @Column (name = "updated_at")
    private OffsetDateTime updatedAt;

}