package br.com.tajetti.backend.relations.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.tajetti.backend.relations.entity.PlaylistTrack;

public interface PlaylistTrackRepository extends JpaRepository<PlaylistTrack, UUID> {
    
}
