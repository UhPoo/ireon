package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.lost.LostCommentController;
import com.uhpoo.ireon.api.controller.lost.request.CreateLostCommentRequest;
import com.uhpoo.ireon.api.controller.lost.request.EditLostCommentRequest;
import com.uhpoo.ireon.api.controller.lost.response.LostCommentResponse;
import com.uhpoo.ireon.api.service.lost.LostCommentQueryService;
import com.uhpoo.ireon.api.service.lost.dto.CreateLostCommentDto;
import com.uhpoo.ireon.api.service.lost.dto.EditLostCommentDto;
import com.uhpoo.ireon.api.service.lost.dto.LostCommentService;
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
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    private final LostCommentQueryService lostCommentQueryService = mock(LostCommentQueryService.class);
    private final LostCommentController lostCommentController = mock(LostCommentController.class);

    @Override
    protected Object initController() {
        return new LostCommentController(lostCommentService, lostCommentQueryService);
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

    @DisplayName("실종동물 댓글 삭제 API")
    @Test
    @WithMockUser
    void deleteLostComment() throws Exception{

        given(lostCommentService.deleteLostComment(anyLong(), anyString()))
                .willReturn(2L);

        mockMvc.perform(
                        delete("/lost/comment/{lostCommentId}", 2L)
                                .header("Authentication", "authentication")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andDo(document("delete-lost-comment",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("lostCommentId").description("삭제할 실종동물 댓글 PK 값")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.NUMBER)
                                        .description("삭제된 PK 값")
                        )
                ));
    }

    @DisplayName("실종동물 댓글 조회 API")
    @Test
    @WithMockUser
    void getLostComment() throws Exception{

        LostCommentResponse item1 = LostCommentResponse.builder()
                .lostCommentId(1L)
                .author("피곤한 직장인")
                .comment("댓글 눈치 게임 1")
                .createdTime("2024-04-16 22:00")
                .build();

        LostCommentResponse item2 = LostCommentResponse.builder()
                .lostCommentId(2L)
                .author("행복한 퇴사자")
                .comment("댓글 눈치 게임 2~")
                .createdTime("2024-04-16 22:10")
                .build();

        LostCommentResponse item3 = LostCommentResponse.builder()
                .lostCommentId(3L)
                .author("잠자던 대학원생")
                .comment("댓글 눈치 게임 2!")
                .createdTime("2024-04-16 22:10")
                .build();


        List<LostCommentResponse> items = List.of(item1, item2, item3);

        PageResponse<List<LostCommentResponse>> response = PageResponse.of(false, items);

        given(lostCommentQueryService.getLostComment(anyLong(), anyLong()))
                .willReturn(response);

        mockMvc.perform(
                        get("/lost/comment/{lostId}", 1L, 3L)
                                .header("Authentication", "authentication")
                                .queryParam("lastCommentId", "0")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-lost-comments",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("lostId").description("실종동물 게시글 PK")
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
                                fieldWithPath("data.items[].lostCommentId").type(JsonFieldType.NUMBER)
                                        .description("유기동물 게시글 댓글 PK"),
                                fieldWithPath("data.items[].author").type(JsonFieldType.STRING)
                                        .description("작성자"),
                                fieldWithPath("data.items[].comment").type(JsonFieldType.STRING)
                                        .description("댓글 내용"),
                                fieldWithPath("data.items[].createdTime").type(JsonFieldType.STRING)
                                        .description("작성시간")
                        )
                ));
    }
}
