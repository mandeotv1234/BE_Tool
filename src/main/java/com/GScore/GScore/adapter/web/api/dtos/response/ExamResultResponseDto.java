package com.GScore.GScore.adapter.web.api.dtos.response;

import com.GScore.GScore.application.usecases.response.ExamResultResponse;
import lombok.Builder;

@Builder
public record ExamResultResponseDto(
        Long registrationNumber,
        Double math,
        Double literature,
        Double foreignLanguage,
        String foreignLanguageCode,
        Double physics,
        Double chemistry,
        Double biology,
        Double history,
        Double geography,
        Double civicEducation
) {
    public static ExamResultResponseDto from(ExamResultResponse response) {
        return ExamResultResponseDto.builder()
                .registrationNumber(response.registrationNumber())
                .math(response.math())
                .literature(response.literature())
                .foreignLanguage(response.foreignLanguage())
                .foreignLanguageCode(response.foreignLanguageCode())
                .physics(response.physics())
                .chemistry(response.chemistry())
                .biology(response.biology())
                .history(response.history())
                .geography(response.geography())
                .civicEducation(response.civicEducation())
                .build();
    }
}
