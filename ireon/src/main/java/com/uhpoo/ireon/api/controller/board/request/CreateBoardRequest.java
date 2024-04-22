package com.uhpoo.ireon.api.controller.board.request;

import com.uhpoo.ireon.api.service.board.dto.CreateBoardDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 자유게시판 게시글 등록 요청 DTO
 *
 * @author 최영환
 */
@Data
@NoArgsConstructor
public class CreateBoardRequest {

    private String title;
    private String content;

    @Builder
    public CreateBoardRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public CreateBoardDto toDto() {
        return null;
    }
}
