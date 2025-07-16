package com.GScore.GScore.infrastructure.services;

import com.GScore.GScore.application.port.services.JwtService;
import com.GScore.GScore.domain.exceptions.JwtInvalidException;
import com.GScore.GScore.domain.models.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    private final Key key;
    private final long jwtTokenValidity;

    public JwtServiceImpl(
            @Value("${application.security.jwt.secret-key}") String secretKey,
            @Value("${application.security.jwt.expiration}") long jwtTokenValidity
    ) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.jwtTokenValidity = jwtTokenValidity;
    }

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .setClaims(Map.of("username", user.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtTokenValidity))
                .signWith(key)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            throw new JwtInvalidException("Invalid JWT: " + e.getMessage());
        }
    }

    @Override
    public String extractUsername(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.get("username", String.class);
    }
}
