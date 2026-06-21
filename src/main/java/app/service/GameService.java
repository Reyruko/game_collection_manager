package app.service;

import app.exception.GameNotFoundException;
import app.exception.UserNotFoundException;
import app.mapper.GameMapper;
import app.model.dto.game.GameDTO;
import app.model.entity.Game;
import app.model.entity.User;
import app.repository.GameRepository;
import app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final UserRepository userRepository;
    private final UserGameService userGameService;

    public List<GameDTO> getAllGames() {
        return gameRepository.findAll().stream().map(gameMapper::toDTO).toList();
    }

    public void addToGameLibrary(String username, UUID id) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        Game game = gameRepository.findById(id).orElseThrow(GameNotFoundException::new);

        userGameService.addGame(user, game);
    }

}
