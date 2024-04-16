package com.uhpoo.ireon.api.service.abandon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 유기동물 게시글 댓글 생성 DTO
 *
 * @author 최영환
 */
@Data
@NoArgsConstructor
public class CreateAbandonCommentDto {

    private Long abandonId;
    private String content;
}
