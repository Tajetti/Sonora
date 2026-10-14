package br.com.tajetti.backend.relations.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.tajetti.backend.relations.entity.UserTrack;

public interface UserTrackRepository extends JpaRepository<UserTrack, UUID> {
    
}
