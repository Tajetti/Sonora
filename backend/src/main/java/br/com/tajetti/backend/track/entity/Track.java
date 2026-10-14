package br.com.tajetti.backend.track.entity;

import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Entity 
@Table(name = "tracks")
public class Track {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @Column (name = "source_id")
    private String sourceId;

    @Column (name = "source_url")
    private String sourceUrl;

    @Column (name = "thumbnail_url")
    private String thumbnailUrl;
    
    @Column (name = "channel_name")
    private String channelName;
    
    @Column (name = "uploader_name")
    private String uploaderName;

    @Column (name = "duration_seconds")
    private Integer durationSeconds;

    @CreationTimestamp 
    @Column (name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp 
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

}