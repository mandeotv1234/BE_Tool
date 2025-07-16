package com.GScore.GScore.infrastructure.seeding;

import com.GScore.GScore.application.port.repositories.ExamResultRepository;
import com.GScore.GScore.domain.models.ExamResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile({"development", "local", "production"})
public class ExamResultSeeder {

    private final ExamResultRepository examResultRepository;

    @Value("classpath:data/diem_thi_thpt_2024.csv")
    private Resource csvFile;

    @EventListener(ApplicationReadyEvent.class)
    public void initSeed() {
        try {
            // Tự động seed khi ứng dụng hoàn toàn ready
            log.info("Application ready, initiating seeding process...");
            seed();
        } catch (Exception e) {
            log.error("Failed to initiate seeding process", e);
            // Không throw exception để không crash ứng dụng
        }
    }


    public void seed() {
        try {
            log.info("Starting ExamResult seeding process...");
            
            if (!csvFile.exists()) {
                log.error("CSV file not found: {}", csvFile.getDescription());
                return;
            }

            log.info("CSV file found: {}, size: {} bytes", csvFile.getDescription(), csvFile.contentLength());

            BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream(), StandardCharsets.UTF_8));
            // Đọc toàn bộ dòng vào list
            List<String> lines = reader.lines().toList();
            
            log.info("Total lines in CSV: {}", lines.size());

            int savedCount = 0;
            int skippedCount = 0;
            List<ExamResult> batchResults = new ArrayList<>();
            final int BATCH_SIZE = 1000;
            log.info("Starting seeding process with batch size: {}", lines);
            log.info("Total lines to process: {}", lines.size());
            // Bỏ qua dòng header (giả sử là dòng đầu tiên)
            for (int i = 1; i <= lines.size() - 1; i++) {
                String line = lines.get(i);
                String[] columns = line.split(",");
                log.info("Processing line {}: {}", i, line);
                if (columns.length < 11) {
                    skippedCount++;
                    log.debug("Skipping line {}: {}", i, line);
                    continue;
                }

                Long registrationNumber = parseLong(columns[0]);
                if (registrationNumber == null) {
                    skippedCount++;
                    log.info("Skipping line {}: {}", i, line);
                    continue;
                }
                
                // Kiểm tra xem record đã tồn tại chưa
                if (examResultRepository.existsByRegistrationNumber(registrationNumber)) {
                    log.info("Skipping existing registration number: {}", registrationNumber);
                    skippedCount++;
                    continue; // Bỏ qua nếu đã tồn tại
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

               // batchResults.add(result);
                
                // Lưu theo batch để tối ưu hiệu suất
//                if (batchResults.size() >= BATCH_SIZE) {
//                    examResultRepository.saveAll(batchResults);
//                    savedCount += batchResults.size();
//                    batchResults.clear();
//                    log.info("Processed {} records so far...", savedCount);
//                }
//            }
//
//            // Lưu batch cuối cùng
//            if (!batchResults.isEmpty()) {
//                examResultRepository.saveAll(batchResults);
//                savedCount += batchResults.size();
//            }
                examResultRepository.save(result);
                log.info("Saved registration number: {}", registrationNumber);
                savedCount++;

            }
            
            log.info("Seeding completed! Saved: {}, Skipped: {}", savedCount, skippedCount);
            
        } catch (Exception e) {
            log.error("Error during seeding process: ", e);
            throw new RuntimeException("Failed to seed exam results", e);
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
