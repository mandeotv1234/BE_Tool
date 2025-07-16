package com.GScore.GScore.application.usecases.response;

public record ScoreLevelReportResponse(
        int excellentCount,
        int goodCount,
        int averageCount,
        int poorCount
) {}
