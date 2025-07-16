package com.GScore.GScore.application.port.repositories;

import com.GScore.GScore.application.usecases.response.ScoreLevelReportResponse;
import com.GScore.GScore.application.usecases.response.SubjectScoreLevelChartResponse;
import com.GScore.GScore.application.usecases.response.Top10GroupAResponse;
import com.GScore.GScore.domain.models.ExamResult;
import com.GScore.GScore.domain.models.PaginationParams;

import java.util.List;
import java.util.Optional;

public interface ExamResultRepository {
    Optional<ExamResult> findByRegistrationNumber(Long registrationNumber);
    Top10GroupAResponse findTop10GroupA(PaginationParams paginationParams);
    List<ExamResult> findAll();
    ExamResult save(ExamResult result);
    List<ExamResult> saveAll(List<ExamResult> results);
    ScoreLevelReportResponse generateScoreLevelReport();
    SubjectScoreLevelChartResponse generateSubjectScoreLevelChart();
    int countTotalStudents();
}
