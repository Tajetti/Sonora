package br.com.tajetti.backend.track.service;

import java.util.List;

import org.springframework.stereotype.Service;
import br.com.tajetti.backend.track.dto.response.SearchTrackResponseDTO;


@Service 
public class TrackService {
    
    public final YtDlpService ytDlpService;

    public TrackService(YtDlpService ytDlpService) {
        this.ytDlpService = ytDlpService;
    }

    public List<SearchTrackResponseDTO> searchTracks(String query, int quantityMusic) {
        if (query == null || query.isBlank()) {
            throw new IllegalArgumentException(
                "A pesquisa não pode ser vazia"
            );
        }

        if(quantityMusic <= 0) {
            throw new IllegalArgumentException(
                "A quantidade de músicas deve ser maior que zero"
            );
        }

        if (quantityMusic > 20) {
            throw new IllegalArgumentException(
                "A quantidade de músicas deve ser menor ou igual a 20"
            );
        }

        return ytDlpService.searchTracks(query, quantityMusic);
    }
}
