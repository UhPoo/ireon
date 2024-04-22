package com.uhpoo.ireon.api.controller.abandon.request;

import com.uhpoo.ireon.api.service.abandon.dto.EditAbandonCommentDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 유기동물 게시글 댓글 수정 요청 DTO
 *
 * @author 최영환
 */
@Data
@NoArgsConstructor
public class EditAbandonCommentRequest {

    private Long commentId;
    private String content;

    @Builder
    public EditAbandonCommentRequest(Long commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }

    public EditAbandonCommentDto toDto() {
        return null;
    }
}
