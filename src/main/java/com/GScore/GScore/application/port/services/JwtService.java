package com.GScore.GScore.application.port.services;

import com.GScore.GScore.domain.models.User;

public interface JwtService {
    String generateToken(User user);
    boolean validateToken(String token);
    String extractUsername(String token);
}
