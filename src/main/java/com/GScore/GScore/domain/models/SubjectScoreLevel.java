package com.GScore.GScore.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubjectScoreLevel {
    private final String subjectName;
    private final String level;
    private final Long count;
}
