package com.GScore.GScore.adapter.web.api.dtos.request;

import com.GScore.GScore.application.usecases.request.AuthenticationRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AuthenticationRequestDto(
        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "Password is required")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{8,}$",
                message = "Password must be at least 8 characters and include 1 uppercase letter, 1 number, and 1 special character"
        )
        String password
) {
    public static AuthenticationRequest from(AuthenticationRequestDto dto) {
        return new AuthenticationRequest(dto.username(), dto.password());
    }
}
