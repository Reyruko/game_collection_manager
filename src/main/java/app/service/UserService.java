package app.service;

import app.exception.*;
import app.mapper.UserMapper;
import app.model.dto.user.ChangePasswordRequest;
import app.model.dto.user.UserDTO;
import app.model.dto.user.UserEditProfileRequest;
import app.model.dto.user.UserRegisterRequest;
import app.model.entity.User;
import app.model.enums.UserRole;
import app.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
public class UserService implements UserDetailsService{

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
        user.setCreatedOn(LocalDate.now());

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
            throw new PasswordMismatchException("Passwords dont match!");
        }
    }

    public UserDTO findByUsername(String username) {

        User user = userRepository.findByUsername(username).orElseThrow(() ->
                        new UsernameNotFoundException(username));

        return userMapper.toUserDTO(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("ROLE_" + user.getRole())
                .build();
    }

    public void updateProfile(String username, UserEditProfileRequest dto) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(dto.getCurrentPassword(),
                user.getPassword())) {
            throw new PasswordMismatchException("Current password is incorrect");
        }

        if (!user.getUsername().equals(dto.getUsername())
                && userRepository.existsByUsername(dto.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }

        if (!user.getEmail().equals(dto.getEmail())
                && userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        user.setUsername(dto.getUsername().trim());
        user.setEmail(dto.getEmail().trim());
        user.setBio(dto.getBio()!= null ? dto.getBio().trim() : null);

        userRepository.save(user);
    }

    public void changePassword(String username, ChangePasswordRequest dto) {

        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())) {
            throw new PasswordMismatchException("Current password is incorrect");
        }

        if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
            throw new PasswordMismatchException("Passwords do not match");
        }

        if (passwordEncoder.matches(dto.getNewPassword(), user.getPassword())) {
            throw new PasswordMismatchException("New password must differ from current password");
        }

        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));

        userRepository.save(user);
    }
}
