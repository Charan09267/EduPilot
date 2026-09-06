package net.edupilot.userservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.edupilot.userservice.dto.LoginRequest;
import net.edupilot.userservice.dto.LoginResponse;
import net.edupilot.userservice.dto.RegisterRequest;
import net.edupilot.userservice.dto.UserResponse;
import net.edupilot.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {

        UserResponse response = userService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {

        LoginResponse response = userService.login(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(
            Authentication authentication
    ) {

        Long userId = (Long) authentication.getPrincipal();

        UserResponse response =
                userService.getCurrentUser(userId);

        return ResponseEntity.ok(response);
    }
}
