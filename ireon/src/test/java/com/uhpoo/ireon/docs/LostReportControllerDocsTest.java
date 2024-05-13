package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.controller.lost.LostReportController;
import com.uhpoo.ireon.api.service.lost.LostReportService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 실종동물 신고 API Docs 테스트 컨트롤러
 *
 * @author 최예지
 */
@WebMvcTest(LostReportControllerDocsTest.class)
public class LostReportControllerDocsTest extends RestDocsSupport{
    private final LostReportService lostReportService= mock(LostReportService.class);

    @Override
    protected Object initController() {
        return new LostReportController(lostReportService);
    }
    @DisplayName("실종동물 게시글 신고 등록 API")
    @Test
    @WithMockUser
    void createLostReport() throws Exception{
        given(lostReportService.createLostReport(anyLong(), anyString()))
                .willReturn(1L);

        mockMvc.perform(
                        post("/lost/report/{lostId}", 1L)
                                .header("Authentication", "authentication")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("create-lost-report",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("lostId").description("실종동물 게시글 PK")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.NUMBER)
                                        .description("등록된 실종동물 게시글 신고 PK 값")
                        )
                ));
    }

    @DisplayName("실종동물 게시글 신고 삭제 API")
    @Test
    @WithMockUser
    void deleteLostReport() throws  Exception{
        given(lostReportService.deleteLostReport(anyLong(), anyString()))
                .willReturn(2L);

        mockMvc.perform(
                        delete("/lost/report/{lostReportId}", 2L)
                                .header("Authentication", "authentication")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andDo(document("delete-lost-report",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("lostReportId").description("삭제할 실종동물 신고 PK 값")
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
}
