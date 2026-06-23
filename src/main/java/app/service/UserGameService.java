package app.service;

import app.exception.UserNotFoundException;
import app.model.entity.User;
import app.model.entity.UserGame;
import app.repository.UserGameRepository;
import app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGameService {

    private final UserGameRepository userGameRepository;
    private final UserRepository userRepository;

    public List<UserGame> getUserGames(String username){
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        return userGameRepository.findAllByUser(user);
    }

}