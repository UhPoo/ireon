package com.uhpoo.ireon.api.controller.lost.request;

import com.uhpoo.ireon.api.service.lost.dto.EditLostCommentDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 실종동물 댓글 수정 요청 DTO
 *
 * @author CYJ
 */
@Data
@NoArgsConstructor
public class EditLostCommentRequest {

    private Long lostCommentId;
    private String comment;

    @Builder
    public EditLostCommentRequest(Long lostCommentId, String comment) {
        this.lostCommentId = lostCommentId;
        this.comment = comment;
    }

    public EditLostCommentDto toDto(){
        return EditLostCommentDto.builder()
                .lostCommentId(this.lostCommentId)
                .comment(this.comment)
                .build();
    }
}
