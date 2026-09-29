package br.com.tajetti.backend.track;

import java.util.UUID;

import org.hibernate.annotations.DialectOverride.GeneratedColumns;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Track {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

}
