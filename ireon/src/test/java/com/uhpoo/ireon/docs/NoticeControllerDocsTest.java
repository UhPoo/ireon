package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.controller.notice.NoticeController;
import com.uhpoo.ireon.api.controller.notice.request.CreateNoticeRequest;
import com.uhpoo.ireon.api.service.notice.NoticeService;
import com.uhpoo.ireon.api.service.notice.dto.CreateNoticeDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 공지사항 API Docs 테스트
 *
 * @author 최영환
 */
@WebMvcTest(NoticeControllerDocsTest.class)
public class NoticeControllerDocsTest extends RestDocsSupport {

    private final NoticeService noticeService = mock(NoticeService.class);

    @Override
    protected Object initController() {
        return new NoticeController(noticeService);
    }

    @DisplayName("공지사항 게시글 등록 API")
    @Test
    @WithMockUser
    void createNotice() throws Exception {
        CreateNoticeRequest request = CreateNoticeRequest.builder()
                .title("제목")
                .content("내용")
                .build();

        given(noticeService.createNotice(any(CreateNoticeDto.class), anyString()))
                .willReturn(1L);

        mockMvc.perform(
                        post("/notice")
                                .header("Authentication", "authentication")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("create-notice",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("게시글 제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING)
                                        .description("게시글 내용")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.NUMBER)
                                        .description("등록된 공지사항 게시글 PK 값")
                        )
                ));
    }

}
