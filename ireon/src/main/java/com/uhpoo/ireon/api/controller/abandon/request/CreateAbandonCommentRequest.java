package com.uhpoo.ireon.api.controller.abandon.request;

import com.uhpoo.ireon.api.service.abandon.dto.CreateAbandonCommentDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 유기동물 게시글 댓글 생성 요청 DTO
 *
 * @author 최영환
 */
@Data
@NoArgsConstructor
public class CreateAbandonCommentRequest {

    private Long abandonId;
    private String content;

    @Builder
    public CreateAbandonCommentRequest(Long abandonId, String content) {
        this.abandonId = abandonId;
        this.content = content;
    }

    public CreateAbandonCommentDto toDto() {
        return null;
    }
}
