package com.GScore.GScore.application.usecases;

import com.GScore.GScore.application.port.repositories.ExamResultRepository;
import com.GScore.GScore.application.usecases.request.TopGroupARequest;
import com.GScore.GScore.application.usecases.response.ExamResultResponse;
import com.GScore.GScore.application.usecases.response.Top10GroupAResponse;
import com.GScore.GScore.domain.models.PaginationParams;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetTop10GroupAUseCase {
    private final ExamResultRepository repository;

    public Top10GroupAResponse execute(TopGroupARequest request) {
        PaginationParams paginationParams = PaginationParams.of(request.page(), request.size());
        return repository.findTop10GroupA(paginationParams);
    }
}

