package com.uhpoo.ireon.api.controller.lost.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 실종동물 게시글 신고 조회 응답 DTO
 *
 * @author 최예지
 */
@Data
@NoArgsConstructor
public class LostReportResponse {
    private Long lostReportId;
    private Long lostId;
    private String content;
    private String createdTime;

    @Builder
    public LostReportResponse(Long lostReportId, Long lostId, String content, String createdTime) {
        this.lostReportId = lostReportId;
        this.lostId = lostId;
        this.content = content;
        this.createdTime = createdTime;
    }
}
