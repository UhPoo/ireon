package com.uhpoo.ireon.api.controller.abandon.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 유기동물 게시글 댓글 조회 DTO
 *
 * @author 최영환
 */
@Data
@NoArgsConstructor
public class AbandonCommentResponse {

    private Long commentId;
    private String author;
    private String content;
    private String createdTime;

    @Builder
    public AbandonCommentResponse(Long commentId, String author, String content, String createdTime) {
        this.commentId = commentId;
        this.author = author;
        this.content = content;
        this.createdTime = createdTime;
    }
}
