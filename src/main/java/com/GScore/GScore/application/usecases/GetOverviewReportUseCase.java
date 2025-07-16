package com.GScore.GScore.application.usecases;

import com.GScore.GScore.application.port.repositories.ExamResultRepository;
import com.GScore.GScore.application.usecases.response.OverviewReportResponse;
import com.GScore.GScore.infrastructure.persistence.repositories.jpa.ExamResultJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
public class GetOverviewReportUseCase {
    private final ExamResultRepository examResultRepository;

    public OverviewReportResponse execute() {
        int totalStudents = (int) examResultRepository.countTotalStudents();
        return new OverviewReportResponse(totalStudents);
    }
}