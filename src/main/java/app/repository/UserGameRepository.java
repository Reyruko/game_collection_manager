package app.repository;

import app.model.entity.Game;
import app.model.entity.User;
import app.model.entity.UserGame;
import app.model.enums.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserGameRepository extends JpaRepository<UserGame, UUID> {
    Optional<UserGame> findByUser(User user);

    List<UserGame> findAllByUserAndStatus(User user, GameStatus status);

    boolean existsByUserAndGame(User user, Game game);

    List<UserGame> findAllByUserAndFavoriteTrue(User user);

    List<UserGame> findAllByUser(User user);
}
