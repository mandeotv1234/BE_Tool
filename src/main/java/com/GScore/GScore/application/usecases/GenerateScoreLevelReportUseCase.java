package com.GScore.GScore.application.usecases;

import com.GScore.GScore.application.usecases.response.ScoreLevelReportResponse;
import com.GScore.GScore.application.port.repositories.ExamResultRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GenerateScoreLevelReportUseCase {
    private final ExamResultRepository repository;

    public ScoreLevelReportResponse execute() {
        return repository.generateScoreLevelReport();
    }}
