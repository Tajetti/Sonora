package br.com.tajetti.backend.track.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.tajetti.backend.track.entity.Track;

public interface TrackRepository extends JpaRepository<Track, UUID> {}