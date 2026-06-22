package app.service;

import app.exception.GameNotFoundException;
import app.exception.UnauthorizedException;
import app.exception.UserNotFoundException;
import app.mapper.GameMapper;
import app.model.dto.game.GameDTO;
import app.model.dto.usergame.EditGameLibraryRequest;
import app.model.entity.Game;
import app.model.entity.User;
import app.model.entity.UserGame;
import app.model.enums.GameStatus;
import app.repository.GameRepository;
import app.repository.UserGameRepository;
import app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final UserRepository userRepository;
    private final UserGameRepository userGameRepository;

    public List<GameDTO> getAllGames() {
        return gameRepository.findAll().stream().map(gameMapper::toDTO).toList();
    }

    public boolean addToGameLibrary(String username, UUID gameId) {

        if (userGameRepository.existsByUserUsernameAndGameId(username, gameId)) {
            return false;
        }

        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        Game game = gameRepository.findById(gameId).orElseThrow(GameNotFoundException::new);

        UserGame userGame = new UserGame();
        userGame.setUser(user);
        userGame.setGame(game);
        userGame.setStatus(GameStatus.WISHLIST);
        userGame.setAddedOn(LocalDate.now());

        userGameRepository.save(userGame);

        return true;
    }

    public void removeGame(String username, UUID id) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        user.getGames().removeIf(g -> g.getId().equals(id));
        userRepository.save(user);
    }

    public List<Game> getLatestGames(int limit) {
        return gameRepository.findTop5ByOrderByCreatedOnDesc();
    }

    public void editGameLibrary(String name, UUID id, EditGameLibraryRequest editGameLibraryRequest) {
        UserGame entry = userGameRepository.findById(id).orElseThrow(UserNotFoundException::new);

        if (!entry.getUser().getUsername().equals(name)) {
            throw new UnauthorizedException();
        }

        entry.setStatus(editGameLibraryRequest.getStatus());
        entry.setRating(editGameLibraryRequest.getRating());
        entry.setHoursPlayed(editGameLibraryRequest.getHoursPlayed());

        userGameRepository.save(entry);
    }
}
