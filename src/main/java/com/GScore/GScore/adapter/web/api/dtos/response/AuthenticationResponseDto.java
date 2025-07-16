package com.GScore.GScore.adapter.web.api.dtos.response;

import com.GScore.GScore.application.usecases.response.AuthenticationResponse;
import lombok.Builder;

@Builder
public record AuthenticationResponseDto(
        String accessToken
) {

    public static AuthenticationResponseDto from(AuthenticationResponse authenticationResponse) {
        return AuthenticationResponseDto.builder()
                .accessToken(authenticationResponse.accessToken())
                .build();
    }
}