package com.uhpoo.ireon.api.controller.board.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardResponse {

    private Long boardId;
    private String title;
    private String author;
    private Boolean clipped;
    private String createdDate;

    @Builder
    public BoardResponse(Long boardId, String title, String author, Boolean clipped, String createdDate) {
        this.boardId = boardId;
        this.title = title;
        this.author = author;
        this.clipped = clipped;
        this.createdDate = createdDate;
    }
}
