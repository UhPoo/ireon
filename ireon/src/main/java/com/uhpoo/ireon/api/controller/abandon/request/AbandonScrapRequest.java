package com.uhpoo.ireon.api.controller.abandon.request;

import com.uhpoo.ireon.api.service.abandon.dto.AbandonScrapDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AbandonScrapRequest {

    private Long abandonId;

    @Builder
    public AbandonScrapRequest(Long abandonId) {
        this.abandonId = abandonId;
    }

    public AbandonScrapDto toDto() {
        return null;
    }
}
