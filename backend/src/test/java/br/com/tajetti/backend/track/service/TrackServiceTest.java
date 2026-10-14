package br.com.tajetti.backend.track.service;

import org.junit.jupiter.api.Test;

public class TrackServiceTest {
    
    @Test 
    void ShouldExecuteYtDlp() {
        TrackService trackService = new TrackService();

        trackService.searchTracks("beber beber leonardo");
    }
}
