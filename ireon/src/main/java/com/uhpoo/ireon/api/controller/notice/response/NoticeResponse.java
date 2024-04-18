package com.uhpoo.ireon.api.controller.notice.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeResponse {

    private Long noticeId;
    private String title;
    private String author;
    private String createdDate;

    @Builder
    public NoticeResponse(Long noticeId, String title, String author, String createdDate) {
        this.noticeId = noticeId;
        this.title = title;
        this.author = author;
        this.createdDate = createdDate;
    }
}
