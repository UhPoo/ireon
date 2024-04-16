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

    private String comment;
    private Boolean active;

    @Builder
    public CreateLostCommentRequest(String comment, Boolean active) {
        this.comment = comment;
        this.active = active;
    }

    public CreateLostCommentDto toDto() {
        return CreateLostCommentDto.builder()
                .comment(this.comment)
                .active(this.active)
                .build();
    }
}
