package br.com.tajetti.backend.track.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class SearchTrackResponseDTO {
    private String sourceId;
    private String title;
    private String sourceUrl;
    private String thumbnailUrl;
    private String channelName;
    private String uploaderName;
    private Integer durationSeconds;
}
