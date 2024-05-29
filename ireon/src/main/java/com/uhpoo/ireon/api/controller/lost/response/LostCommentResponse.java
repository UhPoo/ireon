package com.uhpoo.ireon.api.controller.lost.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 실종동물 댓글 조회 응답 DTO
 *
 * @author CYJ
 */
@Data
@NoArgsConstructor
public class LostCommentResponse {
    private Long lostCommentId;
    private String author;
    private String comment;
    private String createdTime;

    @Builder
    public LostCommentResponse(Long lostCommentId, String comment, String author, String createdTime) {
        this.lostCommentId = lostCommentId;
        this.author = author;
        this.comment = comment;
        this.createdTime = createdTime;
    }
}
