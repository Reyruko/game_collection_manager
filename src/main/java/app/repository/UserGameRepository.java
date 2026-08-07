package app.repository;

import app.model.entity.User;
import app.model.entity.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserGameRepository extends JpaRepository<UserGame, UUID> {
    int countByUserId(UUID userId);
    List<UserGame> findAllByUser(User user);
    boolean existsByUserUsernameAndGameId(String username, UUID gameId);
    UUID user(User user);
    Optional<UserGame> findByUserAndGameId(User user, UUID id);

    List<UserGame> findByUserId(UUID userId);

    Collection<Object> findAllByUserId(UUID userId);
}
