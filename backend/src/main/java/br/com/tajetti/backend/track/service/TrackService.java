package br.com.tajetti.backend.track.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import br.com.tajetti.backend.track.dto.response.SearchTrackResponseDTO;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;


@Service 
public class TrackService {
    
    public List<SearchTrackResponseDTO> searchTracks(String query, int quantityMusic) {

        if(quantityMusic <= 0) {
            throw new IllegalArgumentException("A quantidade de músicas deve ser maior que zero.");
        } else if(quantityMusic > 15) {
            throw new IllegalArgumentException("A quantidade de músicas deve ser menor ou igual a 15.");
        }

        List<SearchTrackResponseDTO> tracks = new ArrayList<>();

        ProcessBuilder builder = new ProcessBuilder( // Escreve um comando externo a aplicacao
            "yt-dlp", 
            "ytsearch" + quantityMusic + ":" + query, 
            "--dump-json"
        );

        //builder.redirectErrorStream(true);

        try{
            Process process = builder.start(); // Executa o comando externo

            InputStream inputStream = process.getInputStream(); // Pega o resultado do comando externo em bytes
            
            try(BufferedReader reader = new BufferedReader // BufferedReader é um facilitador para ler caracteres de um InputStream(Junta uma palavra: c-a-c-h-o-r-r-o para cachorro).
                (new InputStreamReader(inputStream, StandardCharsets.UTF_8))) { // Essa linha transforma os bytes em caracteres usando UTF-8

                ObjectMapper objectMapper = new ObjectMapper();
                
                String line;
            
                while ((line = reader.readLine()) != null) {
                    SearchTrackResponseDTO track = new SearchTrackResponseDTO();

                    JsonNode jsonNode = objectMapper.readTree(line);

                    String title = jsonNode.get("title").asString();
                    String sourceId = jsonNode.get("id").asString();
                    String sourceUrl = jsonNode.get("webpage_url").asString();
                    String thumbnailUrl = jsonNode.get("thumbnail").asString();
                    String channelName = jsonNode.get("channel").asString();
                    String uploaderName = jsonNode.get("uploader").asString();
                    Integer durationSeconds = jsonNode.get("duration").asInt();

                    track.setTitle(title);
                    track.setSourceId(sourceId);
                    track.setSourceUrl(sourceUrl);
                    track.setThumbnailUrl(thumbnailUrl);
                    track.setChannelName(channelName);
                    track.setUploaderName(uploaderName);
                    track.setDurationSeconds(durationSeconds);

                    tracks.add(track);
                }

                int exitCode = process.waitFor();
                
                if(exitCode != 0) {
                    throw new IllegalStateException("Process exited with code: " + exitCode);
                }
            }
        } catch (IOException e) {
           throw new IllegalStateException("Erro ao pesquisar músicas: ", e);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Pesquisa de músicas interrompidas", ie);
        }
        
        return tracks;
    }
}
