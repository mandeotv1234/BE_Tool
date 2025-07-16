package com.GScore.GScore.application.usecases;

import com.GScore.GScore.application.port.repositories.ExamResultRepository;
import com.GScore.GScore.application.usecases.response.ExamResultResponse;
import com.GScore.GScore.domain.exceptions.ExamResultNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetExamResultByIdUseCase {

    private final ExamResultRepository repository;

    public ExamResultResponse execute(Long registrationNumber) {
        var examResult = repository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() ->
                        new ExamResultNotFoundException("No exam result found for registration number: " + registrationNumber));

        return new ExamResultResponse(
                examResult.getRegistrationNumber(),
                examResult.getMath(),
                examResult.getLiterature(),
                examResult.getForeignLanguage(),
                examResult.getForeignLanguageCode(),
                examResult.getPhysics(),
                examResult.getChemistry(),
                examResult.getBiology(),
                examResult.getHistory(),
                examResult.getGeography(),
                examResult.getCivicEducation()
        );
    }
}
