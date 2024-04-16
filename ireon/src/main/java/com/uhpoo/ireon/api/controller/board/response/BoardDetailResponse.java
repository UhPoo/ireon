package com.uhpoo.ireon.api.controller.board.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardDetailResponse {

    private Long boardId;
    private String author;
    private String title;
    private String content;
    private Boolean clipped;
    private String createdDate;

    @Builder
    public BoardDetailResponse(Long boardId, String author, String title, String content, Boolean clipped,
                               String createdDate) {
        this.boardId = boardId;
        this.author = author;
        this.title = title;
        this.content = content;
        this.clipped = clipped;
        this.createdDate = createdDate;
    }
}
