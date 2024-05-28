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
    private String title;
    private String createdTime;

    @Builder
    public LostReportResponse(Long lostReportId, Long lostId, String title, String createdTime) {
        this.lostReportId = lostReportId;
        this.lostId = lostId;
        this.title = title;
        this.createdTime = createdTime;
    }
}
