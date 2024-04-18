package com.uhpoo.ireon.api.controller.notice.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeDetailResponse {

    private Long noticeId;
    private String author;
    private String title;
    private String content;
    private String createdDate;

    @Builder
    public NoticeDetailResponse(Long noticeId, String author, String title, String content, String createdDate) {
        this.noticeId = noticeId;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }
}
