
package com.GScore.GScore.adapter.web.api.controller;

import com.GScore.GScore.adapter.web.api.dtos.request.AuthenticationRequestDto;
import com.GScore.GScore.adapter.web.api.dtos.response.AuthenticationResponseDto;
import com.GScore.GScore.adapter.web.api.dtos.response.ResponseDto;
import com.GScore.GScore.application.usecases.LoginUsecase;
import com.GScore.GScore.application.usecases.request.AuthenticationRequest;
import com.GScore.GScore.application.usecases.response.AuthenticationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginUsecase loginUsecase;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody @Valid AuthenticationRequestDto authenticationRequestDto) {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(
                authenticationRequestDto.username(),
                authenticationRequestDto.password());
        AuthenticationResponse authenticationResponse = loginUsecase.execute(authenticationRequest);
        AuthenticationResponseDto authenticationResponseDto = AuthenticationResponseDto.from(authenticationResponse);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(authenticationResponseDto));
    }

}