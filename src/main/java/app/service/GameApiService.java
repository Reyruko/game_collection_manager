package app.service;

import app.model.dto.game.GameCreateRequest;
import app.model.dto.game.GameDTO;
import app.model.dto.game.GameUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameApiService {

    private final RestClient gameRestClient;

    public List<GameDTO> getAllGames() {
        return gameRestClient.get().retrieve().body(new ParameterizedTypeReference<List<GameDTO>>() {
        });
    }

    public GameDTO getGameById(UUID id) {
        return gameRestClient.get().uri("/{id}", id).retrieve().body(GameDTO.class);
    }

    public GameDTO createGame(GameCreateRequest request) {
        return gameRestClient.post().body(request).retrieve().body(GameDTO.class);
    }

    public GameDTO updateGame(UUID id, GameUpdateRequest request) {
        return gameRestClient.put().uri("/{id}", id).body(request).retrieve().body(GameDTO.class);
    }

    public void deleteGame(UUID id) {
        gameRestClient.delete().uri("/{id}", id).retrieve().toBodilessEntity();
    }

    public List<GameDTO> getLatestGames() {
        return gameRestClient.get().uri("/latest").retrieve().body(new ParameterizedTypeReference<List<GameDTO>>() {
        });
    }
}
