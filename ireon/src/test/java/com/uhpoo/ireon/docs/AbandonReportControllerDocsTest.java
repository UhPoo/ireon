package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.controller.abandon.AbandonReportController;
import com.uhpoo.ireon.api.service.abandon.AbandonReportService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 유기동물 게시판 신고 API Docs 테스트
 *
 * @author 최영환
 */
@WebMvcTest(AbandonReportControllerDocsTest.class)
public class AbandonReportControllerDocsTest extends RestDocsSupport {

    private final AbandonReportService abandonReportService = mock(AbandonReportService.class);

    @Override
    protected Object initController() {
        return new AbandonReportController(abandonReportService);
    }

    @DisplayName("유기동물 게시글 신고 등록 API")
    @Test
    void createAbandonReport() throws Exception {
        given(abandonReportService.createAbandonReport(anyLong(), anyString()))
                .willReturn(1L);

        mockMvc.perform(
                        post("/abandon/report/{abandonId}", 1L)
                                .header("Authentication", "authentication")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("create-abandon-report",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("abandonId").description("유기동물 게시글 PK")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.NUMBER)
                                        .description("등록된 유기동물 게시글 신고 PK 값")
                        )
                ));
    }
}
