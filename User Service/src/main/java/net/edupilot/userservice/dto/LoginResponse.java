package net.edupilot.userservice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    private String token;
    private UserResponse user;
}
