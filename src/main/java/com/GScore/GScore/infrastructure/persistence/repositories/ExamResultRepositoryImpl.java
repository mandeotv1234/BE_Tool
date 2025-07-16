package com.GScore.GScore.infrastructure.persistence.repositories;

import com.GScore.GScore.application.port.repositories.ExamResultRepository;
import com.GScore.GScore.application.usecases.response.ScoreLevelReportResponse;
import com.GScore.GScore.application.usecases.response.SubjectScoreLevelChartResponse;
import com.GScore.GScore.application.usecases.response.Top10GroupAResponse;
import com.GScore.GScore.domain.models.ExamResult;
import com.GScore.GScore.domain.models.GroupAStudentScore;
import com.GScore.GScore.domain.models.PaginationParams;
import com.GScore.GScore.domain.models.SubjectScoreLevel;
import com.GScore.GScore.domain.models.enums.ScoreLevel;
import com.GScore.GScore.infrastructure.persistence.projections.ScoreLevelCountProjection;
import com.GScore.GScore.infrastructure.persistence.projections.SubjectScoreLevelChartProjection;
import com.GScore.GScore.infrastructure.persistence.repositories.jpa.ExamResultJpaRepository;
import com.GScore.GScore.infrastructure.persistence.entities.ExamResultEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ExamResultRepositoryImpl implements ExamResultRepository {

    private final ExamResultJpaRepository examResultJpaRepository;

    @Override
    public Optional<ExamResult> findByRegistrationNumber(Long registrationNumber) {
        return examResultJpaRepository.findByRegistrationNumber(registrationNumber)
                .map(ExamResultEntity::toModel);
    }

    @Override
    public Top10GroupAResponse findTop10GroupA(PaginationParams paginationParams) {
        Pageable pageable = PageRequest.of(paginationParams.getPage(), paginationParams.getSize());

        List<GroupAStudentScore> scores = examResultJpaRepository.findTop10GroupA(pageable)
                .stream()
                .map(entity -> {
                    ExamResult model = entity.toModel();
                    return GroupAStudentScore.builder()
                            .registrationNumber(model.getRegistrationNumber())
                            .math(model.getMath())
                            .physics(model.getPhysics())
                            .chemistry(model.getChemistry())
                            .totalScore(
                                    (model.getMath() != null ? model.getMath() : 0.0) +
                                            (model.getPhysics() != null ? model.getPhysics() : 0.0) +
                                            (model.getChemistry() != null ? model.getChemistry() : 0.0)
                            )
                            .build();
                })
                .toList();

        return new Top10GroupAResponse(scores);
    }


    @Override
    public List<ExamResult> findAll() {
        return examResultJpaRepository.findAll()
                .stream()
                .map(ExamResultEntity::toModel)
                .toList();
    }

    @Override
    public ExamResult save(ExamResult result) {
        ExamResultEntity entity = ExamResultEntity.fromModel(result);
        return examResultJpaRepository.save(entity).toModel();
    }

    // ✅ Optional: Batch insert
    @Override
    public List<ExamResult> saveAll(List<ExamResult> results) {
        List<ExamResultEntity> entities = results.stream()
                .map(ExamResultEntity::fromModel)
                .toList();
        return examResultJpaRepository.saveAll(entities)
                .stream()
                .map(ExamResultEntity::toModel)
                .toList();
    }

    @Override
    public ScoreLevelReportResponse generateScoreLevelReport() {
        List<ScoreLevelCountProjection> counts = examResultJpaRepository.countByScoreLevel();

        int excellent = 0, good = 0, average = 0, poor = 0;
        for (ScoreLevelCountProjection row : counts) {
            ScoreLevel level = ScoreLevel.valueOf(row.getLevel());
            switch (level) {
                case EXCELLENT -> excellent = row.getCount();
                case GOOD -> good = row.getCount();
                case AVERAGE -> average = row.getCount();
                case POOR -> poor = row.getCount();
            }
        }

        return new ScoreLevelReportResponse(excellent, good, average, poor);
    }

    @Override
    public SubjectScoreLevelChartResponse generateSubjectScoreLevelChart() {
        List<SubjectScoreLevelChartProjection> projections = examResultJpaRepository.countBySubjectAndScoreLevel();

        List<SubjectScoreLevel> data = projections.stream()
                .map(proj -> new SubjectScoreLevel(
                        proj.getSubjectName(),
                        proj.getLevel(),
                        proj.getCount()
                ))
                .toList();

        return new SubjectScoreLevelChartResponse(data);
    }

    @Override
    public int countTotalStudents() {
        return (int) examResultJpaRepository.count();
    }
}
