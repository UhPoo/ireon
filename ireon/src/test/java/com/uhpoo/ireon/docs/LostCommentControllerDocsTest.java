package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.controller.lost.LostCommentController;
import com.uhpoo.ireon.api.controller.lost.request.CreateLostCommentRequest;
import com.uhpoo.ireon.api.controller.lost.request.EditLostCommentRequest;
import com.uhpoo.ireon.api.service.lost.dto.CreateLostCommentDto;
import com.uhpoo.ireon.api.service.lost.dto.EditLostCommentDto;
import com.uhpoo.ireon.api.service.lost.dto.LostCommentService;
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
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 실종동물 댓글 APIDocs 테스트
 *
 * @author yekki
 */
@WebMvcTest(LostCommentControllerDocsTest.class)
public class LostCommentControllerDocsTest extends RestDocsSupport  {

    private final LostCommentService lostCommentService = mock(LostCommentService.class);
    private final LostCommentController lostCommentController = mock(LostCommentController.class);

    @Override
    protected Object initController() {
        return new LostCommentController(lostCommentService);
    }

    @DisplayName("실종동물 댓글 등록 API")
    @Test
    @WithMockUser
    void createLostComment() throws Exception{
        CreateLostCommentRequest request = CreateLostCommentRequest.builder()
                .lostId(1L)
                .comment("오 이친구 본 거 같아용")
                .build();


        given(lostCommentService.createLostComment(any(CreateLostCommentDto.class), anyString()))
                .willReturn(2L);

        mockMvc.perform(
                post("/lost/comment")
                    .header("Authentication", "authentication")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("create-lost-comment",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("lostId").type(JsonFieldType.NUMBER)
                                        .description("실종동물 게시글 PK"),
                                fieldWithPath("comment").type(JsonFieldType.STRING)
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
                                        .description("등록된 PK 값")
                        )
                ));
    }

    @DisplayName("실종동물 댓글 수정 API")
    @Test
    @WithMockUser
    void editLostComment() throws Exception{
        EditLostCommentRequest request = EditLostCommentRequest.builder()
                .lostCommentId(1L)
                .comment("오 이친구 본 거 같아요 => 아니었던 거 같아용")
                .build();


        given(lostCommentService.editLostComment(any(EditLostCommentDto.class), anyString()))
                .willReturn(2L);

        mockMvc.perform(
                        patch("/lost/comment")
                                .header("Authentication", "authentication")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("edit-lost-comment",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("lostCommentId").type(JsonFieldType.NUMBER)
                                        .description("실종동물 댓글 PK"),
                                fieldWithPath("comment").type(JsonFieldType.STRING)
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
                                        .description("수정된 PK 값")
                        )
                ));
    }
}
