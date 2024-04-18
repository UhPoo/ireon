package com.uhpoo.ireon.api.controller.notice.request;

import com.uhpoo.ireon.api.service.notice.dto.CreateNoticeDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateNoticeRequest {

    private String title;
    private String content;

    @Builder
    public CreateNoticeRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public CreateNoticeDto toDto() {
        return null;
    }
}
