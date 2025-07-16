package com.GScore.GScore.application.usecases;

import com.GScore.GScore.application.port.repositories.ExamResultRepository;
import com.GScore.GScore.application.usecases.response.SubjectScoreLevelChartResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GenerateSubjectScoreLevelChartUseCase {

    private final ExamResultRepository examResultRepository;

    public SubjectScoreLevelChartResponse execute() {
        return examResultRepository.generateSubjectScoreLevelChart();
    }
}

