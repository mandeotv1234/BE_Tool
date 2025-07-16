package com.GScore.GScore.domain.models.enums;

public enum ScoreLevel {
    EXCELLENT,  // >= 8
    GOOD,       // >= 6 && < 8
    AVERAGE,    // >= 4 && < 6
    POOR;
    public static ScoreLevel fromScore(Double score) {
        if (score == null) return null;
        if (score >= 8.0) return EXCELLENT;
        if (score >= 6.0) return GOOD;
        if (score >= 4.0) return AVERAGE;
        return POOR;
    }
}

