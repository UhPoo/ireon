package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.abandon.AbandonCommentController;
import com.uhpoo.ireon.api.controller.abandon.request.CreateAbandonCommentRequest;
import com.uhpoo.ireon.api.controller.abandon.request.EditAbandonCommentRequest;
import com.uhpoo.ireon.api.controller.abandon.response.AbandonCommentResponse;
import com.uhpoo.ireon.api.service.abandon.AbandonCommentQueryService;
import com.uhpoo.ireon.api.service.abandon.AbandonCommentService;
import com.uhpoo.ireon.api.service.abandon.dto.CreateAbandonCommentDto;
import com.uhpoo.ireon.api.service.abandon.dto.EditAbandonCommentDto;
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
 * 유기동물 게시판 댓글 API Docs 테스트
 *
 * @author 최영환
 */
@WebMvcTest(AbandonControllerDocsTest.class)
public class AbandonCommentControllerDocsTest extends RestDocsSupport {

    private final AbandonCommentService commentService = mock(AbandonCommentService.class);
    private final AbandonCommentQueryService commentQueryService = mock(AbandonCommentQueryService.class);

    @Override
    protected Object initController() {
        return new AbandonCommentController(commentService, commentQueryService);
    }

    @DisplayName("유기동물 게시판 댓글 등록 API")
    @Test
    @WithMockUser
    void createAbandonComment() throws Exception {
        CreateAbandonCommentRequest request = CreateAbandonCommentRequest.builder()
                .abandonId(1L)
                .content("댓글이에요")
                .build();

        given(commentService.createComment(any(CreateAbandonCommentDto.class), anyString()))
                .willReturn(1L);

        mockMvc.perform(
                        post("/abandon/comment")
                                .header("Authentication", "authentication")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("create-abandon-comment",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("abandonId").type(JsonFieldType.NUMBER)
                                        .description("유기동물 게시글 PK"),
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

    @DisplayName("유기동물 게시판 댓글 조회 API")
    @Test
    @WithMockUser
    void getComment() throws Exception {
        AbandonCommentResponse item1 = AbandonCommentResponse.builder()
                .commentId(2L)
                .content("댓글 테스트")
                .author("작성자2")
                .createdTime("2024-04-16 15:12")
                .build();

        AbandonCommentResponse item2 = AbandonCommentResponse.builder()
                .commentId(1L)
                .content("댓글 테스트")
                .author("작성자1")
                .createdTime("2024-04-15 15:15")
                .build();

        List<AbandonCommentResponse> items = List.of(item1, item2);

        PageResponse<List<AbandonCommentResponse>> response = PageResponse.of(false, items);

        given(commentQueryService.getComment(anyLong(), anyLong()))
                .willReturn(response);

        mockMvc.perform(
                        get("/abandon/comment/{abandonId}", 1L)
                                .header("Authentication", "authentication")
                                .queryParam("lastCommentId", "0")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-abandon-comment",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("abandonId").description("유기동물 게시글 PK")
                        ),
                        queryParameters(
                                parameterWithName("lastCommentId").description("마지막으로 조회된 댓글 PK")
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
                                fieldWithPath("data.items[].commentId").type(JsonFieldType.NUMBER)
                                        .description("유기동물 게시글 댓글 PK"),
                                fieldWithPath("data.items[].author").type(JsonFieldType.STRING)
                                        .description("작성자"),
                                fieldWithPath("data.items[].content").type(JsonFieldType.STRING)
                                        .description("댓글 내용"),
                                fieldWithPath("data.items[].createdTime").type(JsonFieldType.STRING)
                                        .description("작성일")
                        )
                ));
    }

    @DisplayName("유기동물 게시판 댓글 수정 API")
    @Test
    @WithMockUser
    void editAbandonComment() throws Exception {
        EditAbandonCommentRequest request = EditAbandonCommentRequest.builder()
                .commentId(1L)
                .content("댓글 수정 테스트")
                .build();

        given(commentService.editComment(any(EditAbandonCommentDto.class), anyString()))
                .willReturn(1L);

        mockMvc.perform(
                        patch("/abandon/comment")
                                .header("Authentication", "authentication")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("edit-abandon-comment",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("commentId").type(JsonFieldType.NUMBER)
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

    @DisplayName("유기동물 게시판 댓글 삭제 API")
    @Test
    @WithMockUser
    void deleteAbandonComment() throws Exception {
        given(commentService.deleteComment(anyLong(), anyString()))
                .willReturn(1L);

        mockMvc.perform(
                        delete("/abandon/comment/{commentId}", 1L)
                                .header("Authentication", "authentication")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andDo(document("delete-abandon-comment",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("commentId").description("삭제할 댓글 PK 값")
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
