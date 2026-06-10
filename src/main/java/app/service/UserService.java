package app.service;

import app.exception.*;
import app.mapper.UserMapper;
import app.model.dto.user.UserDTO;
import app.model.dto.user.UserLoginRequest;
import app.model.dto.user.UserRegisterRequest;
import app.model.entity.User;
import app.model.enums.UserRole;
import app.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserDTO register(UserRegisterRequest userRegisterRequest) {

        validateRegistration(userRegisterRequest);

        User user = userMapper.toEntity(userRegisterRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRole(UserRole.USER);
        user.setCreatedOn(LocalDateTime.now());

        User saved = userRepository.save(user);

        return userMapper.toUserDTO(saved);
    }

    public UserDTO login(UserLoginRequest userLoginRequest) {

        User user = userRepository.findByUsername(userLoginRequest.getUsername())
                .orElseThrow(InvalidUsernameOrPasswordException::new);

        if (!passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
            throw new InvalidUsernameOrPasswordException();
        }

        if(!user.isActive()) {
            throw new UserInactiveException();
        }

        return userMapper.toUserDTO(user);
    }

    private void validateRegistration(UserRegisterRequest userRegisterRequest) {
        if (userRepository.findByUsername(userRegisterRequest.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException();
        }

        if (userRepository.existsByEmail(userRegisterRequest.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        if (!userRegisterRequest.getPassword()
                .equals(userRegisterRequest.getConfirmPassword())) {
            throw new PasswordMismatchException();
        }
    }
}
