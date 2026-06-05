package app.service;

import app.exception.EmailAlreadyExistsException;
import app.exception.PasswordMismatchException;
import app.exception.UsernameAlreadyExistsException;
import app.mapper.UserMapper;
import app.model.dto.user.UserDTO;
import app.model.dto.user.UserRegisterRequest;
import app.model.entity.User;
import app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserDTO register(UserRegisterRequest userRegisterRequest) {
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

        User user = userMapper.toEntity(userRegisterRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User saved = userRepository.save(user);

        return userMapper.toUserDTO(saved);
    }
}
