package br.com.tajetti.backend.track.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.tajetti.backend.track.dto.response.SearchTrackResponseDTO;

public class TrackServiceTest {
    
    @Test 
    void shouldExecuteYtDlpAndReturnFiveResults() {
        TrackService trackService = new TrackService();

        List<SearchTrackResponseDTO> tracks = trackService.searchTracks("beber beber leonardo");

        assertNotNull(tracks.get(0).getTitle());
        assertNotNull(tracks.get(0).getSourceId());

        assertEquals(5, tracks.size());
    }
}
