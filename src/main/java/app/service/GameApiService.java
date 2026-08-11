package app.service;

import app.game.GameClient;
import app.model.dto.game.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameApiService {

    private final GameClient gameClient;

    @Cacheable("games")
    public List<GameDTO> getAllGames() {
        return gameClient.getAllGames();
    }

    @Cacheable(value = "game", key = "#id")
    public GameDTO getGameById(UUID id) {
        return gameClient.getGameById(id);
    }

    @CacheEvict(value = {"games", "latestGames"}, allEntries = true)
    public GameDTO createGame(GameCreateRequest request) {
        return gameClient.createGame(request);
    }

    @CacheEvict(value = {"games", "latestGames", "game"}, allEntries = true)
    public GameDTO updateGame(UUID id, GameUpdateRequest request) {
        return gameClient.updateGame(id, request);
    }

    @CacheEvict(value = {"games", "latestGames", "game"}, allEntries = true)
    public void deleteGame(UUID id) {
        gameClient.deleteGame(id);
    }

    @Cacheable("latestGames")
    public List<GameDTO> getLatestGames() {
        return gameClient.getLatestGames();
    }

    @Cacheable("genres")
    public List<GenreDTO> getAllGenres() {
        return gameClient.getAllGenres();
    }

    @Cacheable("platforms")
    public List<PlatformDTO> getAllPlatforms() {
        return gameClient.getAllPlatforms();
    }

    @CacheEvict(value = "genres", allEntries = true)
    public GenreDTO createGenre(GenreCreateRequest request) {
        return gameClient.createGenre(request);
    }

    @CacheEvict(value = "platforms", allEntries = true)
    public PlatformDTO createPlatform(PlatformCreateRequest request) {
        return gameClient.createPlatform(request);
    }

}
