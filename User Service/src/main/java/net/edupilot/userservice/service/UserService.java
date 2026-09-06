package net.edupilot.userservice.service;

import jakarta.ws.rs.ForbiddenException;
import lombok.RequiredArgsConstructor;
import net.edupilot.commonlibrary.Exception.BadRequestException;
import net.edupilot.commonlibrary.Exception.ResourceNotFoundException;
import net.edupilot.commonlibrary.Exception.UnauthorizedException;
import net.edupilot.userservice.dto.LoginRequest;
import net.edupilot.userservice.dto.LoginResponse;
import net.edupilot.userservice.dto.RegisterRequest;
import net.edupilot.userservice.dto.UserResponse;
import net.edupilot.userservice.entities.User;
import net.edupilot.userservice.entities.UserStatus;
import net.edupilot.userservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import net.edupilot.commonlibrary.JwtUtil.JwtService;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public UserResponse register(RegisterRequest request) {

        String email = request.getEmail().trim().toLowerCase();
        String username = request.getUsername().trim();

        if (userRepository.existsByEmail(email)) {
            throw new BadRequestException("Email already exists");
        }

        if (userRepository.existsByUsername(username)) {
            throw new BadRequestException("Username already exists");
        }

        User user = User.builder()
                .email(email)
                .username(username)
                .firstName(request.getFirstName().trim())
                .lastName(request.getLastName().trim())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }


    private UserResponse mapToResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().name())
                .status(user.getStatus().name())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public LoginResponse login(LoginRequest request) {

        String email = request.getEmail().trim().toLowerCase();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UnauthorizedException("Invalid email or password")
                );

        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new ForbiddenException("User account is not active");
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new UnauthorizedException("Invalid email or password");
        }

        String token = jwtService.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole().name()
        );

        return LoginResponse.builder()
                .token(token)
                .user(mapToResponse(user))
                .build();
    }

    public UserResponse getCurrentUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        return mapToResponse(user);
    }
}