package com.GScore.GScore.infrastructure.seeding;

import com.GScore.GScore.application.port.repositories.ExamResultRepository;
import com.GScore.GScore.domain.models.ExamResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile({"development", "local", "production"})
public class ExamResultSeeder implements ApplicationRunner {

    private final ExamResultRepository examResultRepository;

    @Value("classpath:data/diem_thi_thpt_2024.csv")
    private Resource csvFile;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Application started. Beginning exam result seeding...");

        if (!csvFile.exists()) {
            log.error("CSV file not found: {}", csvFile.getDescription());
            return;
        }

//        if (examResultRepository.countTotalStudents() > 0) {
//            log.info("Data already seeded. Skipping.");
//            return;
//        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream(), StandardCharsets.UTF_8));
        List<String> lines = reader.lines().toList();
        log.info("Total lines in CSV (including header): {}", lines.size());

        int savedCount = 0;
        int skippedCount = 0;

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] columns = line.split(",");
            if (columns.length < 11) {
                skippedCount++;
                log.info("Skipped line {}: {}", i, line);
                continue;
            }

            Long registrationNumber = parseLong(columns[0]);
            if (registrationNumber == null || examResultRepository.existsByRegistrationNumber(registrationNumber)) {
                skippedCount++;
                log.info("Skipped line {}: {}", i, registrationNumber);
                continue;
            }

            ExamResult result = ExamResult.builder()
                    .registrationNumber(registrationNumber)
                    .math(parseDouble(columns[1]))
                    .literature(parseDouble(columns[2]))
                    .foreignLanguage(parseDouble(columns[3]))
                    .physics(parseDouble(columns[4]))
                    .chemistry(parseDouble(columns[5]))
                    .biology(parseDouble(columns[6]))
                    .history(parseDouble(columns[7]))
                    .geography(parseDouble(columns[8]))
                    .civicEducation(parseDouble(columns[9]))
                    .foreignLanguageCode(columns[10])
                    .build();

            examResultRepository.save(result);
            log.info("Saved line {}: {}", i, result);
            savedCount++;
        }

        log.info("Seeding completed. Saved: {}, Skipped: {}", savedCount, skippedCount);
    }

    private Double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) return null;
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Long parseLong(String value) {
        if (value == null || value.trim().isEmpty()) return null;
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
