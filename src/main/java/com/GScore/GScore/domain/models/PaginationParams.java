package com.GScore.GScore.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginationParams {
    private int page;
    private int size;

    public static PaginationParams of(int page, int size) {
        return PaginationParams.builder()
                .page(page)
                .size(size)
                .build();
    }
}
