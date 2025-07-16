package com.GScore.GScore.application.usecases.response;

import com.GScore.GScore.domain.models.SubjectScoreLevel;

import java.util.List;

public record SubjectScoreLevelChartResponse(
        List<SubjectScoreLevel> subjectScoreLevels
) {}
