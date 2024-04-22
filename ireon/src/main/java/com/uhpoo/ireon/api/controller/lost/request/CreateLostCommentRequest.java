package com.uhpoo.ireon.api.controller.lost.request;

import com.uhpoo.ireon.api.service.lost.dto.CreateLostCommentDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 실종동물 댓글 생성 요청 Dto
 *
 * @author yekki
 */
@Data
@NoArgsConstructor
public class CreateLostCommentRequest {

    private Long lostId;
    private String comment;

    @Builder
    public CreateLostCommentRequest(Long lostId, String comment) {
        this.lostId = lostId;
        this.comment = comment;
    }

    public CreateLostCommentDto toDto() {
        return CreateLostCommentDto.builder()
                .lostId(this.lostId)
                .comment(this.comment)
                .build();
    }
}
