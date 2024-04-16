package com.uhpoo.ireon.api.service.lost.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 실종동물 댓글 생성 DTO
 *
 * @author yekk1
 */
@Data
@NoArgsConstructor
public class CreateLostCommentDto {

    private String comment;
    private Boolean active;

    @Builder

    public CreateLostCommentDto(String comment, Boolean active) {
        this.comment = comment;
        this.active = active;
    }
}
