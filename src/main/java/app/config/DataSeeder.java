package app.config;

import app.model.entity.User;
import app.model.enums.UserRole;
import app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        seedAdmin();
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

}
