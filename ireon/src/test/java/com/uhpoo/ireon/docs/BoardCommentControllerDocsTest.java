package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.board.BoardCommentController;
import com.uhpoo.ireon.api.controller.board.request.CreateBoardCommentRequest;
import com.uhpoo.ireon.api.controller.board.request.EditBoardCommentRequest;
import com.uhpoo.ireon.api.controller.board.response.BoardCommentResponse;
import com.uhpoo.ireon.api.service.board.BoardCommentQueryService;
import com.uhpoo.ireon.api.service.board.BoardCommentService;
import com.uhpoo.ireon.api.service.board.dto.CreateBoardCommentDto;
import com.uhpoo.ireon.api.service.board.dto.EditBoardCommentDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
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
    private final BoardCommentQueryService boardCommentQueryService = mock(BoardCommentQueryService.class);

    @Override
    protected Object initController() {
        return new BoardCommentController(boardCommentService, boardCommentQueryService);
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
                                        .description("등록된 자유게시판 댓글 PK 값")
                        )
                ));
    }

    @DisplayName("자유게시판 댓글 조회 API")
    @Test
    @WithMockUser
    void getBoardComments() throws Exception {
        BoardCommentResponse item1 = BoardCommentResponse.builder()
                .boardCommentId(2L)
                .content("댓글 테스트")
                .author("작성자2")
                .createdTime("2024-04-16 15:12")
                .build();

        BoardCommentResponse item2 = BoardCommentResponse.builder()
                .boardCommentId(1L)
                .content("댓글 테스트")
                .author("작성자1")
                .createdTime("2024-04-15 15:15")
                .build();

        List<BoardCommentResponse> items = List.of(item1, item2);

        PageResponse<List<BoardCommentResponse>> response = PageResponse.of(false, items);

        given(boardCommentQueryService.getComments(anyLong(), anyLong()))
                .willReturn(response);

        mockMvc.perform(
                        get("/board/comment/{boardId}", 1L)
                                .header("Authentication", "authentication")
                                .queryParam("lastBoardCommentId", "0")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-board-comments",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("boardId").description("자유게시판 PK")
                        ),
                        queryParameters(
                                parameterWithName("lastBoardCommentId").description("마지막으로 조회된 댓글 PK")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터"),
                                fieldWithPath("data.hasNext").type(JsonFieldType.BOOLEAN)
                                        .description("다음 페이지 존재 여부"),
                                fieldWithPath("data.items").type(JsonFieldType.ARRAY)
                                        .description("댓글 목록"),
                                fieldWithPath("data.items[].boardCommentId").type(JsonFieldType.NUMBER)
                                        .description("자유게시판 댓글 PK"),
                                fieldWithPath("data.items[].author").type(JsonFieldType.STRING)
                                        .description("작성자"),
                                fieldWithPath("data.items[].content").type(JsonFieldType.STRING)
                                        .description("댓글 내용"),
                                fieldWithPath("data.items[].createdTime").type(JsonFieldType.STRING)
                                        .description("작성일")
                        )
                ));
    }

    @DisplayName("자유게시판 댓글 수정 API")
    @Test
    @WithMockUser
    void editBoardComment() throws Exception {
        EditBoardCommentRequest request = EditBoardCommentRequest.builder()
                .boardCommentId(1L)
                .content("댓글 수정 테스트")
                .build();

        given(boardCommentService.editComment(any(EditBoardCommentDto.class), anyString()))
                .willReturn(1L);

        mockMvc.perform(
                        patch("/board/comment")
                                .header("Authentication", "authentication")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("edit-board-comment",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("boardCommentId").type(JsonFieldType.NUMBER)
                                        .description("댓글  PK"),
                                fieldWithPath("content").type(JsonFieldType.STRING)
                                        .description("수정할 댓글 내용")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.NUMBER)
                                        .description("수정된 댓글 PK 값")
                        )
                ));
    }

    @DisplayName("자유게시판 댓글 삭제 API")
    @Test
    @WithMockUser
    void deleteBoardComment() throws Exception {
        given(boardCommentService.deleteBoardComment(anyLong(), anyString()))
                .willReturn(1L);

        mockMvc.perform(
                        delete("/board/comment/{boardCommentId}", 1L)
                                .header("Authentication", "authentication")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andDo(document("delete-board-comment",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("boardCommentId").description("삭제할 댓글 PK 값")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.NUMBER)
                                        .description("삭제된 댓글 PK 값")
                        )
                ));
    }
}
