package br.com.tajetti.backend.track.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.tajetti.backend.track.dto.response.SearchTrackResponseDTO;

public class TrackServiceTest {
    TrackService trackService = new TrackService();

    @Test 
    void shouldExecuteYtDlpAndReturnFiveTracks(){
        List<SearchTrackResponseDTO> execution = trackService.searchTracks("Leonardo", 5);

        assertEquals(5, execution.size());
        assertNotNull(execution.get(0).getTitle());
        assertNotNull(execution.get(0).getChannelName());
    }

    @Test 
    void shouldExecuteYtDlpAndReturnTwentyTracks(){
        List<SearchTrackResponseDTO> execution = trackService.searchTracks("Leonardo", 20);

        assertEquals(20, execution.size());
    }

    @Test 
    void shouldExecuteYtDlpAndReturnIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trackService.searchTracks("Leonardo", 0);
        });

        assertEquals("A quantidade de músicas deve ser maior que zero", exception.getMessage());
    }

    @Test 
    void shouldExecuteSearchTracksAndReturnTracksLimit() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trackService.searchTracks("Leonardo", 21);
        });

        assertEquals("A quantidade de músicas deve ser menor ou igual a 20", exception.getMessage());
    }

    @Test
    void shouldExecuteSearchTracksAndReturnQueryCantBeEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trackService.searchTracks("", 5);
        });

        assertEquals("A pesquisa não pode ser vazia", exception.getMessage());;
    }

    @Test 
    void shouldExecuteSearchTrackAndReturnExceptionSayingQueryNeedsText() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trackService.searchTracks("     ", 5);
        });

        assertEquals("A pesquisa não pode ser vazia", exception.getMessage());

    }

    @Test 
    void shouldExecuteSearchTrackAndReturnExceptionBiggerThanZero(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trackService.searchTracks("leonardo", -1);
        });

        assertEquals("A quantidade de músicas deve ser maior que zero", exception.getMessage());
    }
}
