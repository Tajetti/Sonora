package br.com.tajetti.backend.track.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.tajetti.backend.track.dto.response.SearchTrackResponseDTO;

@ExtendWith (MockitoExtension.class)
public class TrackServiceTest {

    @Mock 
    private YtDlpService ytDlpService;

    @InjectMocks 
    private TrackService trackService;

    @Test
    void shouldReturnFiveTracks() {
        List<SearchTrackResponseDTO> tracks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            SearchTrackResponseDTO track = new SearchTrackResponseDTO();
            track.setTitle("Música " + i);
            track.setChannelName("Canal teste");

            tracks.add(track);
        }

        when(ytDlpService.searchTracks("Leonardo", 5))
            .thenReturn(tracks);

        List<SearchTrackResponseDTO> execution =
            trackService.searchTracks("Leonardo", 5);

        assertEquals(5, execution.size());
        assertNotNull(execution.get(0).getTitle());
        assertNotNull(execution.get(0).getChannelName());

        verify(ytDlpService).searchTracks("Leonardo", 5);
    }

    @Test 
    void shouldExecuteYtDlpAndReturnTwentyTracks(){
        List<SearchTrackResponseDTO> tracks = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            SearchTrackResponseDTO track = new SearchTrackResponseDTO();
            track.setTitle("Música " + i);
            track.setChannelName("Canal teste");

            tracks.add(track);
        }

        when(ytDlpService.searchTracks("Leonardo", 20))
            .thenReturn(tracks);

        List<SearchTrackResponseDTO> execution =
            trackService.searchTracks("Leonardo", 20);

        assertEquals(20, execution.size());
        assertNotNull(execution.get(0).getTitle());
        assertNotNull(execution.get(0).getChannelName());

        verify(ytDlpService).searchTracks("Leonardo", 20);
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
