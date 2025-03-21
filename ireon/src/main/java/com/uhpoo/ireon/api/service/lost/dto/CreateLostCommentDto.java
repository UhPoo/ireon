package com.uhpoo.ireon.api.service.lost.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 실종동물 게시글 댓글 생성 DTO
 *
 * @author CYJ
 */
@Data
@NoArgsConstructor
public class CreateLostCommentDto {

    private Long lostId;
    private String comment;

    @Builder

    public CreateLostCommentDto(Long lostId, String comment) {
        this.lostId = lostId;
        this.comment = comment;
    }
}
