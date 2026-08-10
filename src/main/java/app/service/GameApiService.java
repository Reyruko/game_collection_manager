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

}
