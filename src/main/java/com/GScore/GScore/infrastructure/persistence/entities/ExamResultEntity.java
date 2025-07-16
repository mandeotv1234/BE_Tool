package com.GScore.GScore.infrastructure.persistence.entities;

import com.GScore.GScore.domain.models.ExamResult;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exam_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamResultEntity {

    @Id
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

    public static ExamResultEntity fromModel(ExamResult model) {
        return ExamResultEntity.builder()
                .registrationNumber(model.getRegistrationNumber())
                .math(model.getMath())
                .literature(model.getLiterature())
                .foreignLanguage(model.getForeignLanguage())
                .foreignLanguageCode(model.getForeignLanguageCode())
                .physics(model.getPhysics())
                .chemistry(model.getChemistry())
                .biology(model.getBiology())
                .history(model.getHistory())
                .geography(model.getGeography())
                .civicEducation(model.getCivicEducation())
                .build();
    }

    public ExamResult toModel() {
        return ExamResult.builder()
                .registrationNumber(registrationNumber)
                .math(math)
                .literature(literature)
                .foreignLanguage(foreignLanguage)
                .foreignLanguageCode(foreignLanguageCode)
                .physics(physics)
                .chemistry(chemistry)
                .biology(biology)
                .history(history)
                .geography(geography)
                .civicEducation(civicEducation)
                .build();
    }
}
