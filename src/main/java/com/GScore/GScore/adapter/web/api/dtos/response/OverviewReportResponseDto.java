package com.GScore.GScore.adapter.web.api.dtos.response;

import com.GScore.GScore.application.usecases.response.OverviewReportResponse;

public record OverviewReportResponseDto(int totalStudents) {
    public static OverviewReportResponseDto from(OverviewReportResponse res) {
        return new OverviewReportResponseDto(res.totalStudents());
    }
}