package br.com.tajetti.backend.track.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import br.com.tajetti.backend.track.service.TrackService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrackController.class)
public class TrackControllerTest {
   
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TrackService trackService;
    
    @Test
    void shouldReturnBadRequestWhenQuantityExceedsLimit() throws Exception {
        when(trackService.searchTracks("Leonardo", 21))
            .thenThrow(new IllegalArgumentException(
                "A quantidade de músicas deve ser menor ou igual a 20"
            ));

        mockMvc.perform(get("/api/tracks/search")
                .param("query", "Leonardo")
                .param("numberOfTracks", "21"))
            .andExpect(status().isBadRequest());
    }
}
