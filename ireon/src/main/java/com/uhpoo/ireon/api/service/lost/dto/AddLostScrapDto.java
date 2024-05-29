package com.uhpoo.ireon.api.service.lost.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 실종동물 게시글 스크랩 추가 DTO
 *
 * @author CYJ
 */
@Data
@NoArgsConstructor
public class AddLostScrapDto {
    private Long lostId;

    @Builder
    public AddLostScrapDto(Long lostId) {
        this.lostId = lostId;
    }
}
