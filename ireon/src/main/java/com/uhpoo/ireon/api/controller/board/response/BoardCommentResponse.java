package com.uhpoo.ireon.api.controller.board.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardCommentResponse {

    private Long boardCommentId;
    private String author;
    private String content;
    private String createdTime;

    @Builder
    public BoardCommentResponse(Long boardCommentId, String author, String content, String createdTime) {
        this.boardCommentId = boardCommentId;
        this.author = author;
        this.content = content;
        this.createdTime = createdTime;
    }
}
