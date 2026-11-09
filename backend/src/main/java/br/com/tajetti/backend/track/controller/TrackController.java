package br.com.tajetti.backend.track.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tajetti.backend.track.service.TrackService;
import br.com.tajetti.backend.track.dto.response.SearchTrackResponseDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController 
@RequestMapping("/api/v1/tracks")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping("/search")
    public List<SearchTrackResponseDTO> getTracks(@RequestParam String query, @RequestParam(defaultValue = "5") int numberOfTracks) {
       return trackService.searchTracks(query, numberOfTracks);
    }

}
