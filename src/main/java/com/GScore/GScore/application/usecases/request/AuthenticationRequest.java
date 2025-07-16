package com.GScore.GScore.application.usecases.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AuthenticationRequest(
        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "Password is required")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{8,}$",
                message = "Password must be at least 8 characters and include 1 uppercase letter, 1 number, and 1 special character"
        )
        String password
) {}
