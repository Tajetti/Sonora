package br.com.tajetti.backend.playlist.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.tajetti.backend.playlist.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, UUID> {
    
}
