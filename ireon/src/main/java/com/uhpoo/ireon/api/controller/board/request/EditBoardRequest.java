package com.uhpoo.ireon.api.controller.board.request;

import com.uhpoo.ireon.api.service.board.dto.EditBoardDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditBoardRequest {

    private String title;
    private String content;

    @Builder
    public EditBoardRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public EditBoardDto toDto() {
        return null;
    }
}
