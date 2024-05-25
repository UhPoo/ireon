package com.uhpoo.ireon.api.controller.lost.request;

import com.uhpoo.ireon.api.service.lost.dto.CreateLostReportDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 실종동물 게시글 신고 생성 요청 DTO
 *
 * @author 최예지
 */
@Data
@NoArgsConstructor
public class CreateLostReportRequest {
    private long lostId;
    private String content;

    @Builder
    public CreateLostReportRequest(long lostId, String content) {
        this.lostId = lostId;
        this.content = content;
    }

    public CreateLostReportDto toDto() {
        return CreateLostReportDto.builder()
                .lostId(this.lostId)
                .content(this.content)
                .build();
    }
}
