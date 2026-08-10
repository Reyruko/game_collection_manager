package app.service;

import app.game.GameClient;
import app.model.dto.game.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameApiService {

    private final GameClient gameClient;

    public List<GameDTO> getAllGames() {
        return gameClient.getAllGames();
    }

    public GameDTO getGameById(UUID id) {
        return gameClient.getGameById(id);
    }

    public GameDTO createGame(GameCreateRequest request) {
        return gameClient.createGame(request);
    }

    public GameDTO updateGame(UUID id, GameUpdateRequest request) {
        return gameClient.updateGame(id, request);
    }

    public void deleteGame(UUID id) {
        gameClient.deleteGame(id);
    }

    public List<GameDTO> getLatestGames() {
        return gameClient.getLatestGames();
    }

    public List<GenreDTO> getAllGenres() {
        return gameClient.getAllGenres();
    }

    public List<PlatformDTO> getAllPlatforms() {
        return gameClient.getAllPlatforms();
    }

    public GenreDTO createGenre(GenreCreateRequest request) {
        return gameClient.createGenre(request);
    }

    public PlatformDTO createPlatform(PlatformCreateRequest request) {
        return gameClient.createPlatform(request);
    }

    /*public List<GameDTO> getAllGames() {
        return gameRestClient.get().retrieve().body(new ParameterizedTypeReference<List<GameDTO>>() {
        });
    }

    public GameDTO getGameById(UUID id) {
        return gameRestClient.get().uri("/{id}", id).retrieve().body(GameDTO.class);
    }

    public GameDTO createGame(GameCreateRequest request) {
        try {
            return gameRestClient
                    .post()
                    .body(request)
                    .retrieve()
                    .body(GameDTO.class);

        } catch (HttpClientErrorException ex) {
            throw new GameApiException(
                    ex.getStatusCode().value(),
                    ex.getResponseBodyAsString()
            );
        }
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

    public List<GenreDTO> getAllGenres() {
        return gameRestClient.get()
                .uri("/genres")
                .retrieve()
                .body(new ParameterizedTypeReference<List<GenreDTO>>() {});
    }

    public List<PlatformDTO> getAllPlatforms() {
        return gameRestClient.get()
                .uri("/platforms")
                .retrieve()
                .body(new ParameterizedTypeReference<List<PlatformDTO>>() {});
    }

    public GenreDTO createGenre(GenreCreateRequest request) {

        return gameRestClient
                .post()
                .uri("/genres")
                .body(request)
                .retrieve()
                .body(GenreDTO.class);
    }

    public PlatformDTO createPlatform(PlatformCreateRequest request) {

        return gameRestClient
                .post()
                .uri("/platforms")
                .body(request)
                .retrieve()
                .body(PlatformDTO.class);
    }*/
}
