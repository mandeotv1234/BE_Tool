package com.GScore.GScore.adapter.web.api.dtos.response;

import com.GScore.GScore.application.usecases.response.Top10GroupAResponse;
import com.GScore.GScore.domain.models.GroupAStudentScore;
import lombok.Builder;

import java.util.List;

@Builder
public record Top10GroupAResponseDto (
       List<GroupAStudentScore> groupAStudentScores
) {
    public static Top10GroupAResponseDto from(Top10GroupAResponse top10GroupAResponse) {
       return Top10GroupAResponseDto.builder()
               .groupAStudentScores(top10GroupAResponse.groupAStudentScores())
               .build();
    }
}
