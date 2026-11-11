
package br.com.tajetti.backend.track.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.stereotype.Service;

import br.com.tajetti.backend.track.dto.response.SearchTrackResponseDTO;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class YtDlpService {

    public List<SearchTrackResponseDTO> searchTracks(
        String query,
        int quantityMusic
    ) {
        List<SearchTrackResponseDTO> tracks = new ArrayList<>();

        ProcessBuilder builder = new ProcessBuilder(
            "yt-dlp",
            "ytsearch" + quantityMusic + ":" + query,
            "--dump-json"
        );

        builder.redirectError(
            ProcessBuilder.Redirect.appendTo(
                new File("yt-dlp-erros.log")
            )
        );

        try {
            Process process = builder.start();

            AtomicBoolean timeOut = new AtomicBoolean(false);

            ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();

            ScheduledFuture<?> timeOutTask = scheduler.schedule(() -> {
                if (process.isAlive()) {
                    timeOut.set(true);
                    process.destroyForcibly();
                }
            }, 30, TimeUnit.SECONDS);

            try (
                InputStream inputStream = process.getInputStream();
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        inputStream,
                        StandardCharsets.UTF_8
                    )
                )
            ) {
                ObjectMapper objectMapper = new ObjectMapper();

                String line;

                while ((line = reader.readLine()) != null) {
                    JsonNode jsonNode = objectMapper.readTree(line);

                    SearchTrackResponseDTO track =
                        new SearchTrackResponseDTO();

                    String title = jsonNode.get("title").asString();
                    String sourceId = jsonNode.get("id").asString();
                    String sourceUrl = jsonNode.get("webpage_url").asString();

                    String thumbnailUrl =
                        jsonNode.path("thumbnail").asString(null);

                    String channelName =
                        jsonNode.path("channel").asString(null);

                    String uploaderName =
                        jsonNode.path("uploader").asString(null);

                    JsonNode durationNode = jsonNode.path("duration");

                    Integer durationSeconds =
                        durationNode.isNumber()
                            ? durationNode.asInt()
                            : null;

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

                if (timeOut.get()) {
                    throw new IllegalStateException(
                        "A pesquisa excedeu o tempo limite de 30 segundos"
                    );
                }

                if (exitCode != 0) {
                    throw new IllegalStateException(
                        "Process exited with code: " + exitCode
                    );
                }

            } catch (IOException e) {
                if (timeOut.get()) {
                    throw new IllegalStateException(
                        "A pesquisa excedeu o tempo limite de 30 segundos",
                        e
                    );
                }

                throw new IllegalStateException(
                    "Erro ao ler o resultado da pesquisa",
                    e
                );

            } finally {
                timeOutTask.cancel(false);
                scheduler.shutdownNow();

                if (process.isAlive()) {
                    process.destroyForcibly();
                }
            }

        } catch (IOException e) {
            throw new IllegalStateException(
                "Erro ao iniciar o yt-dlp",
                e
            );

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            throw new IllegalStateException(
                "Pesquisa de músicas interrompida",
                e
            );
        }

        return tracks;
    }
}
