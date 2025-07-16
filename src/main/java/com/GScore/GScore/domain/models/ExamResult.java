package com.GScore.GScore.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExamResult {
    private Long registrationNumber;
    private Double math;
    private Double literature;
    private Double foreignLanguage;
    private String foreignLanguageCode;
    private Double physics;
    private Double chemistry;
    private Double biology;
    private Double history;
    private Double geography;
    private Double civicEducation;
}
