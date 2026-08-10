package app.game;

import app.model.dto.game.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(
        name = "game-service",
        url = "http://localhost:8081")
public interface GameClient {

    @GetMapping("/api/games")
    List<GameDTO> getAllGames();

    @GetMapping("/api/games/{id}")
    GameDTO getGameById(@PathVariable UUID id);

    @PostMapping("/api/games")
    GameDTO createGame(@RequestBody GameCreateRequest request);

    @PutMapping("/api/games/{id}")
    GameDTO updateGame(
            @PathVariable UUID id,
            @RequestBody GameUpdateRequest request
    );

    @DeleteMapping("/api/games/{id}")
    void deleteGame(@PathVariable UUID id);

    @GetMapping("/api/games/latest")
    List<GameDTO> getLatestGames();

    @GetMapping("/api/games/genres")
    List<GenreDTO> getAllGenres();

    @GetMapping("/api/games/platforms")
    List<PlatformDTO> getAllPlatforms();

    @PostMapping("/api/games/genres")
    GenreDTO createGenre(@RequestBody GenreCreateRequest request);

    @PostMapping("/api/games/platforms")
    PlatformDTO createPlatform(@RequestBody PlatformCreateRequest request);
}
