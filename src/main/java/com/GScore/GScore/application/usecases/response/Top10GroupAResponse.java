package com.GScore.GScore.application.usecases.response;

import com.GScore.GScore.domain.models.GroupAStudentScore;
import java.util.List;

public record Top10GroupAResponse(
        List<GroupAStudentScore> groupAStudentScores
) {}
