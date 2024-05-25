package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.lost.LostReportController;
import com.uhpoo.ireon.api.controller.lost.request.CreateLostReportRequest;
import com.uhpoo.ireon.api.controller.lost.response.LostReportResponse;
import com.uhpoo.ireon.api.service.lost.LostReportQueryService;
import com.uhpoo.ireon.api.service.lost.LostReportService;
import com.uhpoo.ireon.api.service.lost.dto.CreateLostReportDto;
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
    private final LostReportQueryService lostReportQueryService = mock(LostReportQueryService.class);

    @Override
    protected Object initController() {
        return new LostReportController(lostReportService, lostReportQueryService);
    }
    @DisplayName("실종동물 게시글 신고 등록 API")
    @Test
    @WithMockUser
    void createLostReport() throws Exception{

        CreateLostReportRequest request = CreateLostReportRequest.builder()
                        .lostId(1L)
                        .content("펫숍 광고입니다.")
                        .build();

        given(lostReportService.createLostReport(any(CreateLostReportDto.class), anyString()))
                .willReturn(1L);

        mockMvc.perform(
                        post("/lost/report/{lostId}", 1L)
                                .header("Authentication", "authentication")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("create-lost-report",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("lostId").type(JsonFieldType.NUMBER)
                                        .description("신고할 실종동물 게시글 PK"),
                                fieldWithPath("content").type(JsonFieldType.STRING)
                                        .description("신고 사유")
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

    @DisplayName("신고한 실종동물 게시글 전체 조회 API")
    @Test
    @WithMockUser
    void getLostReports() throws Exception {
        LostReportResponse item1 = LostReportResponse.builder()
                .lostReportId(1L)
                .lostId(10L)
                .title("강아지 봤어요 뻥이에요.")
                .createdTime("2024-05-01")
                .build();

        LostReportResponse item2 = LostReportResponse.builder()
                .lostReportId(2L)
                .lostId(11L)
                .title("우리 펫숍 이용하세요~")
                .createdTime("2024-05-02")
                .build();

        List<LostReportResponse> items = List.of(item1, item2);

        PageResponse<List<LostReportResponse>> response = PageResponse.of(false, items);

        given(lostReportQueryService.getLostReports(anyString()))
                .willReturn(response);

        mockMvc.perform(
                        get("/lost/report")
                                .header("Authentication", "authentication")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-lost-reports",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
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
                                        .description("게시글 목록"),
                                fieldWithPath("data.items[].lostReportId").type(JsonFieldType.NUMBER)
                                        .description("실종동물 게시글 신고 PK"),
                                fieldWithPath("data.items[].lostId").type(JsonFieldType.NUMBER)
                                        .description("실종동물 게시글 PK"),
                                fieldWithPath("data.items[].title").type(JsonFieldType.STRING)
                                        .description("신고 글 제목"),
                                fieldWithPath("data.items[].createdTime").type(JsonFieldType.STRING)
                                        .description("작성일")
                        )
                ));
    }
}
