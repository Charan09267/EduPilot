package net.edupilot.userservice.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserResponse {

    private Long id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String role;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
