package com.GScore.GScore.adapter.web.api.controller;

import com.GScore.GScore.adapter.web.api.dtos.response.OverviewReportResponseDto;
import com.GScore.GScore.adapter.web.api.dtos.response.ResponseDto;
import com.GScore.GScore.application.usecases.GenerateScoreLevelReportUseCase;
import com.GScore.GScore.adapter.web.api.dtos.response.SubjectScoreLevelChartResponseDto;
import com.GScore.GScore.adapter.web.api.dtos.response.ScoreLevelReportResponseDto;
import com.GScore.GScore.application.usecases.GenerateSubjectScoreLevelChartUseCase;
import com.GScore.GScore.application.usecases.GetOverviewReportUseCase;
import com.GScore.GScore.application.usecases.response.OverviewReportResponse;
import com.GScore.GScore.application.usecases.response.ScoreLevelReportResponse;
import com.GScore.GScore.application.usecases.response.SubjectScoreLevelChartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final GenerateScoreLevelReportUseCase generateScoreLevelReportUseCase;
    private final GenerateSubjectScoreLevelChartUseCase generateSubjectScoreLevelChartUseCase;
    private final GetOverviewReportUseCase getOverviewReportUseCase;

    @GetMapping("/overview")
    public ResponseEntity<ResponseDto> getOverview() {
        OverviewReportResponse overviewReportResponse = getOverviewReportUseCase.execute();
        OverviewReportResponseDto overviewReportResponseDto = OverviewReportResponseDto.from(overviewReportResponse);
        return ResponseEntity.ok(new ResponseDto(overviewReportResponseDto));
    }

    @GetMapping("/score-level")
    public ResponseEntity<ResponseDto> getScoreLevelReport() {
        ScoreLevelReportResponse scoreLevelReportResponse = generateScoreLevelReportUseCase.execute();
        ScoreLevelReportResponseDto scoreLevelReportResponseDto = ScoreLevelReportResponseDto.from(scoreLevelReportResponse);
        return ResponseEntity.ok(new ResponseDto(scoreLevelReportResponseDto));
    }

    @GetMapping("/chart-data")
    public ResponseEntity<ResponseDto> getScoreLevelChartData() {
        SubjectScoreLevelChartResponse subjectScoreLevelChartResponse = generateSubjectScoreLevelChartUseCase.execute();
        SubjectScoreLevelChartResponseDto subjectScoreLevelChartResponseDto = SubjectScoreLevelChartResponseDto.from(subjectScoreLevelChartResponse);
        return ResponseEntity.ok(new ResponseDto(subjectScoreLevelChartResponseDto));
    }
}
