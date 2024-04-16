package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.controller.abandon.AbandonCommentController;
import com.uhpoo.ireon.api.controller.abandon.request.CreateAbandonCommentRequest;
import com.uhpoo.ireon.api.service.abandon.AbandonCommentService;
import com.uhpoo.ireon.api.service.abandon.dto.CreateAbandonCommentDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

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
 * 유기동물 게시판 댓글 API Docs 테스트
 *
 * @author 최영환
 */
@WebMvcTest(AbandonControllerDocsTest.class)
public class AbandonCommentControllerDocsTest extends RestDocsSupport {

    private final AbandonCommentService commentService = mock(AbandonCommentService.class);

    @Override
    protected Object initController() {
        return new AbandonCommentController(commentService);
    }

    @DisplayName("유기동물 게시판 댓글 등록 API")
    @Test
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
}
