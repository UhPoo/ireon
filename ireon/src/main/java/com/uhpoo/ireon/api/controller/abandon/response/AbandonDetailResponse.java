package com.uhpoo.ireon.api.controller.abandon.response;

import com.uhpoo.ireon.domain.abandon.dto.AbandonDetailDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AbandonDetailResponse {

    private AbandonDetailDto dto;
    private List<String> attachments;

    @Builder
    public AbandonDetailResponse(AbandonDetailDto dto, List<String> attachments) {
        this.dto = dto;
        this.attachments = attachments;
    }

    public static AbandonDetailResponse of(AbandonDetailDto dto, List<String> attachments) {
        return AbandonDetailResponse.builder()
                .dto(dto)
                .attachments(attachments)
                .build();
    }
}
