package com.GScore.GScore.application.usecases.response;

public record ExamResultResponse(
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
) {}
