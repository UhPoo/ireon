package com.uhpoo.ireon.api.controller.notice.request;

import com.uhpoo.ireon.api.service.notice.dto.EditNoticeDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditNoticeRequest {

    private Long noticeId;
    private String title;
    private String content;

    @Builder
    public EditNoticeRequest(Long noticeId, String title, String content) {
        this.noticeId = noticeId;
        this.title = title;
        this.content = content;
    }

    public EditNoticeDto toDto() {
        return null;
    }
}
