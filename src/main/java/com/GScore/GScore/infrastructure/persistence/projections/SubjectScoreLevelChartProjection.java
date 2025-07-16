package com.GScore.GScore.infrastructure.persistence.projections;

public interface SubjectScoreLevelChartProjection {
    String getSubjectName();
    String getLevel();
    Long getCount();
}

