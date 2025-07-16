package com.GScore.GScore.infrastructure.seeding;

import com.GScore.GScore.application.port.repositories.ExamResultRepository;
import com.GScore.GScore.domain.models.ExamResult;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class ExamResultSeeder {

    private final ExamResultRepository examResultRepository;

    @Value("classpath:data/diem_thi_thpt_2024.csv")
    private Resource csvFile;

    @PostConstruct
    public void seed() throws Exception {
        if (examResultRepository.countTotalStudents() > 0) {
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream(), StandardCharsets.UTF_8));
        String line;
        boolean firstLine = true;

        while ((line = reader.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                continue;
            }

            String[] columns = line.split(",");
            if (columns.length < 11) continue;

            ExamResult result = ExamResult.builder()
                    .registrationNumber(parseLong(columns[0]))
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
            System.out.println("Saved: " + result.getRegistrationNumber());
        }
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
        return Long.parseLong(value.trim());
    }
}
