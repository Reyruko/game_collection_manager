package app.service;

import app.exception.GameNotFoundException;
import app.exception.UnauthorizedException;
import app.exception.UserNotFoundException;
import app.model.dto.game.GameDTO;
import app.model.dto.usergame.EditGameLibraryRequest;
import app.model.dto.usergame.UserGameProfileDTO;
import app.model.entity.User;
import app.model.entity.UserGame;
import app.model.enums.GameStatus;
import app.repository.UserGameRepository;
import app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserGameService {

    private final UserGameRepository userGameRepository;
    private final UserRepository userRepository;
    private final GameApiService gameApiService;

    /*public List<UserGame> getUserGames(String username){
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        return userGameRepository.findAllByUser(user);
    }*/

    public List<UserGameProfileDTO> getUserGames(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        List<UserGame> userGames = userGameRepository.findAllByUser(user);

        return userGames.stream()
                .map(userGame -> {

                    GameDTO game = gameApiService.getGameById(userGame.getGameId());

                    return new UserGameProfileDTO(
                            userGame.getId(),
                            userGame.getGameId(),
                            game.getName(),
                            userGame.getStatus(),
                            userGame.getHoursPlayed(),
                            userGame.getRating(),
                            userGame.isFavorite()
                    );
                })
                .toList();
    }

    public boolean addToGameLibrary(String username, UUID gameId) {

        if (userGameRepository.existsByUserUsernameAndGameId(username, gameId)) {
            return false;
        }

        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        gameApiService.getGameById(gameId);

        UserGame userGame = new UserGame();
        userGame.setUser(user);
        userGame.setGameId(gameId);
        userGame.setStatus(GameStatus.WISHLIST);
        userGame.setAddedOn(LocalDate.now());

        userGameRepository.save(userGame);

        return true;
    }

    public void removeGame(String username, UUID id) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        UserGame userGame = userGameRepository.findByUserAndGameId((user), id).orElseThrow(GameNotFoundException::new);

        userGameRepository.delete(userGame);
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

    public List<UserGameProfileDTO> getUserGamesWithDetails(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        return userGameRepository.findAllByUser(user)
                .stream()
                .map(userGame -> {

                    GameDTO game = gameApiService.getGameById(userGame.getGameId());

                    return new UserGameProfileDTO(
                            game.getId(),
                            game.getGameId(),
                            game.getName(),
                            userGame.getStatus(),
                            userGame.getHoursPlayed(),
                            userGame.getRating(),
                            userGame.isFavorite()
                    );

                })
                .toList();
    }

}