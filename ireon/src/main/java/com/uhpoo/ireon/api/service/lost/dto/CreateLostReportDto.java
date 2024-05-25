package com.uhpoo.ireon.api.service.lost.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 실종동물 신고 생성 DTO
 * @author 최예지
 */
@Data
@NoArgsConstructor
public class CreateLostReportDto {
    private Long lostId;
    private String content;

    @Builder

    public CreateLostReportDto(Long lostId, String content) {
        this.lostId = lostId;
        this.content = content;
    }
}
