package com.GScore.GScore.adapter.web.api.controller;

import com.GScore.GScore.adapter.web.api.dtos.response.ExamResultResponseDto;
import com.GScore.GScore.adapter.web.api.dtos.response.ResponseDto;
import com.GScore.GScore.adapter.web.api.dtos.response.Top10GroupAResponseDto;
import com.GScore.GScore.application.usecases.GetExamResultByIdUseCase;
import com.GScore.GScore.application.usecases.GetTop10GroupAUseCase;
import com.GScore.GScore.application.usecases.request.TopGroupARequest;
import com.GScore.GScore.application.usecases.response.ExamResultResponse;
import com.GScore.GScore.application.usecases.response.Top10GroupAResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam-results")
@RequiredArgsConstructor
public class ExamResultController {

    private final GetExamResultByIdUseCase getByIdUseCase;
    private final GetTop10GroupAUseCase getTop10GroupAUseCase;

    @GetMapping("/{registrationNumber}")
    public ResponseEntity<ResponseDto> getExamResult(@PathVariable Long registrationNumber) {
        ExamResultResponse examResultResponse = getByIdUseCase.execute(registrationNumber);
        ExamResultResponseDto examResultResponseDto = ExamResultResponseDto.from(examResultResponse);
        return ResponseEntity.ok(new ResponseDto(examResultResponseDto));
    }

    @GetMapping("/top10-group-a")
    public ResponseEntity<ResponseDto> getTop10GroupA(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        TopGroupARequest topGroupARequest = new TopGroupARequest(page, size);
        Top10GroupAResponse top10GroupAResponse = getTop10GroupAUseCase.execute(topGroupARequest);
        Top10GroupAResponseDto top10GroupAResponseDto = Top10GroupAResponseDto.from(top10GroupAResponse);
        return ResponseEntity.ok(new ResponseDto(top10GroupAResponseDto));
    }

}


