package br.com.tajetti.backend.track.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveTrackRequestDTO {
    private String sourceId;
    private String title;
    private String sourceUrl;
    private String thumbnailUrl;
    private String channelName;
    private String uploaderName;
    private Integer durationSeconds;
}