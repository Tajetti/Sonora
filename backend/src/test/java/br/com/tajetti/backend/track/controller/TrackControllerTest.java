package br.com.tajetti.backend.track.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import br.com.tajetti.backend.track.dto.response.SearchTrackResponseDTO;
import br.com.tajetti.backend.track.service.TrackService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@WebMvcTest(TrackController.class) // definindo que a classe vai ser TrackController.
public class TrackControllerTest {
   
    @Autowired
    private MockMvc mockMvc; // permite simular requisições http

    @MockitoBean //Simulamos a requisição (podemos escolher req e retorno). Ele não executa a trackcontroller de verdade.
    private TrackService trackService;
    
    @Test
    void shouldReturnBadRequestWhenQuantityExceedsLimit() throws Exception {
        when(trackService.searchTracks("Leonardo", 21))
            .thenThrow(new IllegalArgumentException(
                "A quantidade de músicas deve ser menor ou igual a 20"
            ));

        mockMvc.perform(get("/api/v1/tracks/search")
                .param("query", "Leonardo")
                .param("numberOfTracks", "21"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnSuccessWhenParamsIsValid() throws Exception{
        when(trackService.searchTracks("Leonardo", 5))
            .thenReturn(List.of());
    
        mockMvc.perform(get("/api/v1/tracks/search")
            .param("query", "Leonardo")
            .param("numberOfTracks", "5"))
        .andExpect(status().isOk())
        .andExpect(content().json("[]"));
    }

    @Test 
    void shouldReturnTrackAsJson() throws Exception{
        SearchTrackResponseDTO track = new SearchTrackResponseDTO();
        track.setSourceId("1231251361247247254");
        track.setTitle("Beber Beber");

        when(trackService.searchTracks("Leonardo", 5))
            .thenReturn(List.of(track));

        mockMvc.perform(get("/api/v1/tracks/search")
            .param("query", "Leonardo")
            .param("numberOfTracks", "5"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].title").value("Beber Beber"));
    }

}
