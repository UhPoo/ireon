package com.uhpoo.ireon.api.controller.board.request;

import com.uhpoo.ireon.api.service.board.dto.CreateBoardCommentDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateBoardCommentRequest {

    private Long boardId;
    private String content;

    @Builder
    public CreateBoardCommentRequest(Long boardId, String content) {
        this.boardId = boardId;
        this.content = content;
    }

    public CreateBoardCommentDto toDto() {
        return null;
    }
}
