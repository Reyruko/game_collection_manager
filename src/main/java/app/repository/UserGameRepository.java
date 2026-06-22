package app.repository;

import app.model.entity.User;
import app.model.entity.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserGameRepository extends JpaRepository<UserGame, UUID> {
    Optional<UserGame> findByUser(User user);
    Optional<UserGame> findByUserUsernameAndGameId(String username, UUID gameId);
    List<UserGame> findAllByUser(User user);
    boolean existsByUserUsernameAndGameId(String username, UUID gameId);
}
