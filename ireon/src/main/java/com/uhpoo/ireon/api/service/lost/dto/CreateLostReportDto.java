package com.uhpoo.ireon.api.service.lost.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 실종동물 게시글 신고 생성 DTO
 * @author CYJ
 */
@Data
@NoArgsConstructor
public class CreateLostReportDto {
    private Long lostId;
    private String reason;

    @Builder

    public CreateLostReportDto(Long lostId, String reason) {
        this.lostId = lostId;
        this.reason = reason;
    }
}
