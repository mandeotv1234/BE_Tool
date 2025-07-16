package com.GScore.GScore.adapter.web.api.controller;

import com.GScore.GScore.infrastructure.seeding.ExamResultSeeder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ExamResultSeeder examResultSeeder;

    @PostMapping("/seed")
    public ResponseEntity<String> triggerSeeding() {
        try {
            examResultSeeder.seed();
            return ResponseEntity.ok("Seeding completed successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Seeding failed: " + e.getMessage());
        }
    }
}
