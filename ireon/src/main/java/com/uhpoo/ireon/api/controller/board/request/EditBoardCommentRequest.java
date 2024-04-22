package com.uhpoo.ireon.api.controller.board.request;

import com.uhpoo.ireon.api.service.board.dto.EditBoardCommentDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditBoardCommentRequest {

    private Long boardCommentId;
    private String content;

    @Builder
    public EditBoardCommentRequest(Long boardCommentId, String content) {
        this.boardCommentId = boardCommentId;
        this.content = content;
    }

    public EditBoardCommentDto toDto() {
        return null;
    }
}
