package com.GScore.GScore.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupAStudentScore {
    private Long registrationNumber;
    private Double math;
    private Double physics;
    private Double chemistry;
    private Double totalScore;
}

