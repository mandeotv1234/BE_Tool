package com.GScore.GScore.adapter.web.api.dtos.response;

import com.GScore.GScore.application.usecases.response.ScoreLevelReportResponse;

public record ScoreLevelReportResponseDto(
        int excellentCount,
        int goodCount,
        int averageCount,
        int poorCount
) {
    public static ScoreLevelReportResponseDto from(ScoreLevelReportResponse response) {
        return new ScoreLevelReportResponseDto(
                response.excellentCount(),
                response.goodCount(),
                response.averageCount(),
                response.poorCount()
        );
    }
}
