package br.com.tajetti.backend.track.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.stereotype.Service;
import br.com.tajetti.backend.track.dto.response.SearchTrackResponseDTO;


@Service 
public class TrackService {
    
    public List<SearchTrackResponseDTO> searchTracks(String query) {
        ProcessBuilder builder = new ProcessBuilder("yt-dlp", "--version");
        try{
            Process process = builder.start();
            
            int exitCode = process.waitFor();
            System.out.println("yt-dlp exit code: " + exitCode);
            
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            
            String line = reader.readLine();
            System.out.println("yt-dlp output: " + line);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return List.of();
    }
}
