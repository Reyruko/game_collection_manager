package app.service;

import app.exception.*;
import app.mapper.UserMapper;
import app.model.dto.user.UserDTO;
import app.model.dto.user.UserRegisterRequest;
import app.model.entity.User;
import app.model.enums.UserRole;
import app.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class UserService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(username)
        );

        if (!user.isActive()) {
            throw new DisabledException(
                    "Account inactive"
            );
        }

        return org.springframework.security.core.userdetails
                .User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
