package app.config;

import app.model.dto.game.GameSeedDTO;
import app.model.entity.Game;
import app.model.entity.User;
import app.model.enums.UserRole;
import app.repository.GameRepository;
import app.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final GameRepository gameRepository;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        seedAdmin();
        seedGames();
    }

    private void seedAdmin() {
        if (userRepository.count() == 0) {
            User admin = User.builder()
                    .username("admin")
                    .email("admin_collector@gmail.com")
                    .password(passwordEncoder.encode("collectorAdmin"))
                    .active(true)
                    .role(UserRole.ADMIN)
                    .build();

            userRepository.save(admin);
        }
    }

    private void seedGames() throws Exception {
        if (gameRepository.count() > 0) {
            return;
        }

        ClassPathResource resource = new ClassPathResource("data/games.json");

        List<GameSeedDTO> seeds =
                objectMapper.readValue(resource.getInputStream(),
                        new TypeReference<List<GameSeedDTO>>() {});

        List<Game> games = seeds
                .stream()
                .map(this::toEntity)
                .toList();

        gameRepository.saveAll(games);
    }

    private Game toEntity(GameSeedDTO dto) {
        return Game.builder()
                .name(dto.getName())
                .slug(dto.getSlug())
                .developer(dto.getDeveloper())
                .publisher(dto.getPublisher())
                .releaseDate(dto.getReleaseDate())
                .description(dto.getDescription())
                .coverImage(dto.getCoverImage())
                .genre(dto.getGenre())
                .platforms(dto.getPlatforms())
                .build();
    }
}
