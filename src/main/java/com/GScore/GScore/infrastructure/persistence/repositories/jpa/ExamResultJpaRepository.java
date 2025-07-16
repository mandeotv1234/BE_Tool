package com.GScore.GScore.infrastructure.persistence.repositories.jpa;

import com.GScore.GScore.infrastructure.persistence.entities.ExamResultEntity;
import com.GScore.GScore.infrastructure.persistence.projections.ScoreLevelCountProjection;
import com.GScore.GScore.infrastructure.persistence.projections.SubjectScoreLevelChartProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;

public interface ExamResultJpaRepository extends JpaRepository<ExamResultEntity, Long> {

    Optional<ExamResultEntity> findByRegistrationNumber(Long registrationNumber);

    @Query("SELECT e FROM ExamResultEntity e " +
            "WHERE e.math IS NOT NULL AND e.physics IS NOT NULL AND e.chemistry IS NOT NULL " +
            "ORDER BY (e.math + e.physics + e.chemistry) DESC")
    List<ExamResultEntity> findTop10GroupA(Pageable pageable);

    @Query("""
    SELECT
                      CASE
                          WHEN (
                                   COALESCE(e.math, 0) + COALESCE(e.literature, 0) + COALESCE(e.foreignLanguage, 0) +
                                   COALESCE(e.physics, 0) + COALESCE(e.chemistry, 0) + COALESCE(e.biology, 0) +
                                   COALESCE(e.history, 0) + COALESCE(e.geography, 0) + COALESCE(e.civicEducation, 0)
                                   ) /
                               NULLIF(
                                       (CASE WHEN e.math IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.literature IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.foreignLanguage IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.physics IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.chemistry IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.biology IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.history IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.geography IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.civicEducation IS NOT NULL THEN 1 ELSE 0 END)
                                   , 0)
                              >= 8 THEN 'EXCELLENT'
                          WHEN (
                                   COALESCE(e.math, 0) + COALESCE(e.literature, 0) + COALESCE(e.foreignLanguage, 0) +
                                   COALESCE(e.physics, 0) + COALESCE(e.chemistry, 0) + COALESCE(e.biology, 0) +
                                   COALESCE(e.history, 0) + COALESCE(e.geography, 0) + COALESCE(e.civicEducation, 0)
                                   ) /
                               NULLIF(
                                       (CASE WHEN e.math IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.literature IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.foreignLanguage IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.physics IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.chemistry IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.biology IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.history IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.geography IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.civicEducation IS NOT NULL THEN 1 ELSE 0 END)
                                   , 0)
                              >= 6 THEN 'GOOD'
                          WHEN (
                                   COALESCE(e.math, 0) + COALESCE(e.literature, 0) + COALESCE(e.foreignLanguage, 0) +
                                   COALESCE(e.physics, 0) + COALESCE(e.chemistry, 0) + COALESCE(e.biology, 0) +
                                   COALESCE(e.history, 0) + COALESCE(e.geography, 0) + COALESCE(e.civicEducation, 0)
                                   ) /
                               NULLIF(
                                       (CASE WHEN e.math IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.literature IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.foreignLanguage IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.physics IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.chemistry IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.biology IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.history IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.geography IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.civicEducation IS NOT NULL THEN 1 ELSE 0 END)
                                   , 0)
                              >= 4 THEN 'AVERAGE'
                          ELSE 'POOR'
                          END AS level,
                      COUNT(*) AS count
                  FROM ExamResultEntity e
                  GROUP BY
                      CASE
                          WHEN (
                                   COALESCE(e.math, 0) + COALESCE(e.literature, 0) + COALESCE(e.foreignLanguage, 0) +
                                   COALESCE(e.physics, 0) + COALESCE(e.chemistry, 0) + COALESCE(e.biology, 0) +
                                   COALESCE(e.history, 0) + COALESCE(e.geography, 0) + COALESCE(e.civicEducation, 0)
                                   ) /
                               NULLIF(
                                       (CASE WHEN e.math IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.literature IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.foreignLanguage IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.physics IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.chemistry IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.biology IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.history IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.geography IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.civicEducation IS NOT NULL THEN 1 ELSE 0 END)
                                   , 0)
                              >= 8 THEN 'EXCELLENT'
                          WHEN (
                                   COALESCE(e.math, 0) + COALESCE(e.literature, 0) + COALESCE(e.foreignLanguage, 0) +
                                   COALESCE(e.physics, 0) + COALESCE(e.chemistry, 0) + COALESCE(e.biology, 0) +
                                   COALESCE(e.history, 0) + COALESCE(e.geography, 0) + COALESCE(e.civicEducation, 0)
                                   ) /
                               NULLIF(
                                       (CASE WHEN e.math IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.literature IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.foreignLanguage IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.physics IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.chemistry IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.biology IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.history IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.geography IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.civicEducation IS NOT NULL THEN 1 ELSE 0 END)
                                   , 0)
                              >= 6 THEN 'GOOD'
                          WHEN (
                                   COALESCE(e.math, 0) + COALESCE(e.literature, 0) + COALESCE(e.foreignLanguage, 0) +
                                   COALESCE(e.physics, 0) + COALESCE(e.chemistry, 0) + COALESCE(e.biology, 0) +
                                   COALESCE(e.history, 0) + COALESCE(e.geography, 0) + COALESCE(e.civicEducation, 0)
                                   ) /
                               NULLIF(
                                       (CASE WHEN e.math IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.literature IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.foreignLanguage IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.physics IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.chemistry IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.biology IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.history IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.geography IS NOT NULL THEN 1 ELSE 0 END) +
                                       (CASE WHEN e.civicEducation IS NOT NULL THEN 1 ELSE 0 END)
                                   , 0)
                              >= 4 THEN 'AVERAGE'
                          ELSE 'POOR'
                  END
                  
""")
    List<ScoreLevelCountProjection> countByScoreLevel();

    @Query(value = """
    SELECT 
        subject_name,
        CASE 
            WHEN score >= 8 THEN 'EXCELLENT'
            WHEN score >= 6 THEN 'GOOD'
            WHEN score >= 4 THEN 'AVERAGE'
            ELSE 'POOR'
        END AS level,
        COUNT(*) AS count
    FROM (
        SELECT registration_number, 'Math' AS subject_name, math AS score FROM exam_results WHERE math IS NOT NULL
        UNION ALL
        SELECT registration_number, 'Literature', literature FROM exam_results WHERE literature IS NOT NULL
        UNION ALL
        SELECT registration_number, 'Foreign Language', foreign_language FROM exam_results WHERE foreign_language IS NOT NULL
        UNION ALL
        SELECT registration_number, 'Physics', physics FROM exam_results WHERE physics IS NOT NULL
        UNION ALL
        SELECT registration_number, 'Chemistry', chemistry FROM exam_results WHERE chemistry IS NOT NULL
        UNION ALL
        SELECT registration_number, 'Biology', biology FROM exam_results WHERE biology IS NOT NULL
        UNION ALL
        SELECT registration_number, 'History', history FROM exam_results WHERE history IS NOT NULL
        UNION ALL
        SELECT registration_number, 'Geography', geography FROM exam_results WHERE geography IS NOT NULL
        UNION ALL
        SELECT registration_number, 'Civic Education', civic_education FROM exam_results WHERE civic_education IS NOT NULL
    ) AS all_scores
    GROUP BY subject_name, level
    ORDER BY subject_name, level;
    """, nativeQuery = true)
    List<SubjectScoreLevelChartProjection> countBySubjectAndScoreLevel();


}
