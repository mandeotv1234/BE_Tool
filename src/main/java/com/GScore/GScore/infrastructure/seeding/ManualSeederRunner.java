package com.GScore.GScore.infrastructure.seeding;

import com.GScore.GScore.application.port.repositories.ExamResultRepository;
import com.GScore.GScore.domain.models.ExamResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ManualSeederRunner implements CommandLineRunner {

    private final ExamResultRepository examResultRepository;

    @Value("classpath:data/diem_thi_thpt_2024.csv")
    private Resource csvFile;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Chỉ chạy nếu có argument --seed
        if (args.length > 0 && "--seed".equals(args[0])) {
            seedData();
        }
    }

    private void seedData() {
        try {
            log.info("Manual seeding started...");
            
            // Kiểm tra xem đã có dữ liệu chưa
            long existingCount = examResultRepository.countTotalStudents();
            if (existingCount > 0) {
                log.info("Database already contains {} exam results. Skipping seeding.", existingCount);
                return;
            }

            if (!csvFile.exists()) {
                log.error("CSV file not found: {}", csvFile.getDescription());
                return;
            }

            log.info("CSV file found: {}, size: {} bytes", csvFile.getDescription(), csvFile.contentLength());

            BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream(), StandardCharsets.UTF_8));
            List<String> lines = reader.lines().toList();
            
            log.info("Total lines in CSV: {}", lines.size());

            int savedCount = 0;
            int skippedCount = 0;
            
            // Bỏ qua dòng header
            for (int i = 1; i <= lines.size() - 1; i++) {
                String line = lines.get(i);
                String[] columns = line.split(",");
                if (columns.length < 11) {
                    skippedCount++;
                    continue;
                }

                Long registrationNumber = parseLong(columns[0]);
                if (registrationNumber == null || examResultRepository.existsByRegistrationNumber(registrationNumber)) {
                    skippedCount++;
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
                savedCount++;
                
                if (savedCount % 1000 == 0) {
                    log.info("Processed {} records so far...", savedCount);
                }
            }
            
            log.info("Manual seeding completed! Saved: {}, Skipped: {}", savedCount, skippedCount);
            
        } catch (Exception e) {
            log.error("Error during manual seeding: ", e);
            throw new RuntimeException("Failed to manually seed exam results", e);
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
