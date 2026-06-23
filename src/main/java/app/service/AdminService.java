package app.service;

import app.exception.UserNotFoundException;
import app.model.dto.user.UserAdminView;
import app.model.entity.User;
import app.model.enums.UserRole;
import app.repository.UserGameRepository;
import app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final UserGameRepository userGameRepository;
    private final ModelMapper modelMapper;

    public void toggleUserStatus(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        user.setActive(!user.isActive());
        userRepository.save(user);
    }

    public void promoteUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        user.setRole(UserRole.ADMIN);
        userRepository.save(user);
    }

    public List<UserAdminView> getAllUsers() {

        return userRepository.findAll().stream().map(user -> {
            UserAdminView dto = modelMapper.map(user, UserAdminView.class);

            dto.setGamesCount(userGameRepository.countByUserId(user.getId()));

            return dto;
        }).toList();
    }

}
