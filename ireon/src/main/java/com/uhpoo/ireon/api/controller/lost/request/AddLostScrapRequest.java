package com.uhpoo.ireon.api.controller.lost.request;

import com.uhpoo.ireon.api.service.lost.dto.AddLostScrapDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 실종동물 스크랩 추가 요청 Dto
 *
 * @author yekki
 */
@Data
@NoArgsConstructor
public class AddLostScrapRequest {
    private Long lostId;

    @Builder
    public AddLostScrapRequest(Long lostId) {
        this.lostId = lostId;
    }

    public AddLostScrapDto toDto(){
        return AddLostScrapDto.builder()
                .lostId(this.lostId)
                .build();
    }
}
