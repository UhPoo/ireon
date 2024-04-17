package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.controller.board.BoardCommentController;
import com.uhpoo.ireon.api.controller.board.request.CreateBoardCommentRequest;
import com.uhpoo.ireon.api.service.board.BoardCommentService;
import com.uhpoo.ireon.api.service.board.dto.CreateBoardCommentDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 자유게시판 댓글 API Docs 테스트
 *
 * @author 최영환
 */
@WebMvcTest(BoardCommentControllerDocsTest.class)
public class BoardCommentControllerDocsTest extends RestDocsSupport {

    private final BoardCommentService boardCommentService = mock(BoardCommentService.class);

    @Override
    protected Object initController() {
        return new BoardCommentController(boardCommentService);
    }

    @DisplayName("자유게시판 댓글 등록 API")
    @Test
    @WithMockUser
    void createBoardComment() throws Exception {
        CreateBoardCommentRequest request = CreateBoardCommentRequest.builder()
                .boardId(1L)
                .content("댓글이에요")
                .build();

        given(boardCommentService.createBoardComment(any(CreateBoardCommentDto.class), anyString()))
                .willReturn(1L);

        mockMvc.perform(
                        post("/board/comment")
                                .header("Authentication", "authentication")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("create-board-comment",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("boardId").type(JsonFieldType.NUMBER)
                                        .description("자유게시판 게시글 PK"),
                                fieldWithPath("content").type(JsonFieldType.STRING)
                                        .description("댓글 내용")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.NUMBER)
                                        .description("등록된 유기동물 게시글 댓글 PK 값")
                        )
                ));
    }
}
