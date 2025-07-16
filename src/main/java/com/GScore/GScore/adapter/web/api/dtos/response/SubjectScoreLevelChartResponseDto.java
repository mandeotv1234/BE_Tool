package com.GScore.GScore.adapter.web.api.dtos.response;

import com.GScore.GScore.application.usecases.response.SubjectScoreLevelChartResponse;
import com.GScore.GScore.domain.models.SubjectScoreLevel;

import java.util.List;

public record SubjectScoreLevelChartResponseDto (
       List<SubjectScoreLevel> subjectScoreLevels
) {
    public static SubjectScoreLevelChartResponseDto from(SubjectScoreLevelChartResponse subjectScoreLevelChartResponse) {
        return new SubjectScoreLevelChartResponseDto(
                subjectScoreLevelChartResponse.subjectScoreLevels());
    }
}
