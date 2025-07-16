package com.GScore.GScore.application.usecases;

import com.GScore.GScore.application.port.repositories.UserRepository;
import com.GScore.GScore.application.port.services.JwtService;
import com.GScore.GScore.application.port.services.PasswordEncoder;
import com.GScore.GScore.application.usecases.request.AuthenticationRequest;
import com.GScore.GScore.application.usecases.response.AuthenticationResponse;
import com.GScore.GScore.domain.exceptions.AuthenticationException;
import com.GScore.GScore.domain.models.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class LoginUsecase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse execute(AuthenticationRequest authenticationRequest) {

        Optional<User> userOptional = userRepository.findByUsername(authenticationRequest.username());

        if (userOptional.isEmpty()) {
            throw new AuthenticationException("Invalid username or password");
        }

        if (!passwordEncoder.matches(authenticationRequest.password(), userOptional.get().getPassword())) {
            throw new AuthenticationException("Invalid username or password");
        }

        String accessToken = jwtService.generateToken(userOptional.get());

         return new AuthenticationResponse(accessToken);
    }



}