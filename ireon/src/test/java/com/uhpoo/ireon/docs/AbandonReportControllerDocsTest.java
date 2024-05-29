package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.abandon.AbandonReportController;
import com.uhpoo.ireon.api.controller.abandon.response.AbandonReportDetailResponse;
import com.uhpoo.ireon.api.controller.abandon.response.AbandonReportResponse;
import com.uhpoo.ireon.api.service.abandon.AbandonReportQueryService;
import com.uhpoo.ireon.api.service.abandon.AbandonReportService;
import com.uhpoo.ireon.domain.abandon.AbandonStatus;
import com.uhpoo.ireon.domain.abandon.VaccinationStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
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
    private final AbandonReportQueryService abandonReportQueryService = mock(AbandonReportQueryService.class);

    @Override
    protected Object initController() {
        return new AbandonReportController(abandonReportService, abandonReportQueryService);
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

    @DisplayName("유기동물 게시글 신고 목록 조회 API")
    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getAbandonReports() throws Exception {
        AbandonReportResponse item1 = AbandonReportResponse.builder()
                .abandonReportId(1L)
                .reporter("신고자1")
                .reason("광고")
                .abandonId(3L)
                .title("제목1")
                .author("작성자1")
                .animalType("개")
                .abandonStatus(AbandonStatus.SEARCHING.getText())
                .roadAddress("서울시 송파구 토성로")
                .jibunAddress("서울시 송파구 풍납동")
                .detailAddress("비밀")
                .phoneNumber("010-1234-5678")
                .createdDate("2024-03-05")
                .build();

        AbandonReportResponse item2 = AbandonReportResponse.builder()
                .abandonReportId(2L)
                .reporter("신고자2")
                .reason("거짓 정보")
                .abandonId(1L)
                .title("제목2")
                .author("작성자2")
                .animalType("고양이")
                .abandonStatus(AbandonStatus.SEARCHING.getText())
                .roadAddress("서울시 송파구 토성로")
                .jibunAddress("서울시 송파구 풍납동")
                .detailAddress("비밀")
                .phoneNumber("010-1234-5678")
                .createdDate("2024-03-04")
                .build();

        List<AbandonReportResponse> items = List.of(item1, item2);

        PageResponse<List<AbandonReportResponse>> response = PageResponse.of(false, items);

        given(abandonReportQueryService.getAbandonReports())
                .willReturn(response);

        mockMvc.perform(
                        get("/abandon/report")
                                .header("Authentication", "authentication")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-abandon-reports",
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
                                fieldWithPath("data.items[].abandonReportId").type(JsonFieldType.NUMBER)
                                        .description("유기동물 게시글 신고 PK"),
                                fieldWithPath("data.items[].reason").type(JsonFieldType.STRING)
                                        .description("신고 사유"),
                                fieldWithPath("data.items[].reporter").type(JsonFieldType.STRING)
                                        .description("신고자"),
                                fieldWithPath("data.items[].abandonId").type(JsonFieldType.NUMBER)
                                        .description("유기동물 게시글 PK"),
                                fieldWithPath("data.items[].title").type(JsonFieldType.STRING)
                                        .description("글 제목"),
                                fieldWithPath("data.items[].author").type(JsonFieldType.STRING)
                                        .description("작성자"),
                                fieldWithPath("data.items[].animalType").type(JsonFieldType.STRING)
                                        .description("동물 종류"),
                                fieldWithPath("data.items[].abandonStatus").type(JsonFieldType.STRING)
                                        .description("유기동물 상태"),
                                fieldWithPath("data.items[].roadAddress").type(JsonFieldType.STRING)
                                        .description("도로명 주소"),
                                fieldWithPath("data.items[].jibunAddress").type(JsonFieldType.STRING)
                                        .description("지번 주소"),
                                fieldWithPath("data.items[].detailAddress").type(JsonFieldType.STRING)
                                        .description("상세 주소"),
                                fieldWithPath("data.items[].phoneNumber").type(JsonFieldType.STRING)
                                        .description("연락처"),
                                fieldWithPath("data.items[].createdDate").type(JsonFieldType.STRING)
                                        .description("작성일")
                        )
                ));
    }

    @DisplayName("유기동물 게시글 신고 상세 조회 API")
    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getAbandonReport() throws Exception {
        AbandonReportDetailResponse response = AbandonReportDetailResponse.builder()
                .abandonReportId(1L)
                .reporter("신고자1")
                .reason("광고")
                .abandonId(3L)
                .title("제목1")
                .author("작성자1")
                .animalType("개")
                .animalDetail("말티즈")
                .animalGender("암컷")
                .animalAge(5)
                .vaccinationStatus(VaccinationStatus.SECOND.getText())
                .neutralized(false)
                .abandonStatus(AbandonStatus.SEARCHING.getText())
                .zipcode("11111")
                .roadAddress("서울시 송파구 토성로")
                .jibunAddress("서울시 송파구 풍납동")
                .detailAddress("비밀")
                .phoneNumber("010-1234-5678")
                .createdDate("2024-03-05")
                .build();

        given(abandonReportQueryService.getAbandonReport(anyLong(), anyString()))
                .willReturn(response);

        mockMvc.perform(
                        get("/abandon/report/{abandonReportId}", 1L)
                                .header("Authentication", "authentication")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-abandon-report",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("abandonReportId").description("유기동물 게시글 신고 PK")
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
                                fieldWithPath("data.abandonReportId").type(JsonFieldType.NUMBER)
                                        .description("유기동물 게시글 신고 PK"),
                                fieldWithPath("data.reason").type(JsonFieldType.STRING)
                                        .description("신고 사유"),
                                fieldWithPath("data..reporter").type(JsonFieldType.STRING)
                                        .description("신고자"),
                                fieldWithPath("data..abandonId").type(JsonFieldType.NUMBER)
                                        .description("유기동물 게시글 PK"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING)
                                        .description("글 제목"),
                                fieldWithPath("data.author").type(JsonFieldType.STRING)
                                        .description("작성자"),
                                fieldWithPath("data.animalType").type(JsonFieldType.STRING)
                                        .description("동물 종류"),
                                fieldWithPath("data.animalDetail").type(JsonFieldType.STRING)
                                        .description("동물 종류 상세"),
                                fieldWithPath("data.animalGender").type(JsonFieldType.STRING)
                                        .description("동물 성별"),
                                fieldWithPath("data.animalAge").type(JsonFieldType.NUMBER)
                                        .description("동물 나이"),
                                fieldWithPath("data.vaccinationStatus").type(JsonFieldType.STRING)
                                        .description("예방접종 상태"),
                                fieldWithPath("data.neutralized").type(JsonFieldType.BOOLEAN)
                                        .description("중성화 여부"),
                                fieldWithPath("data.abandonStatus").type(JsonFieldType.STRING)
                                        .description("유기동물 상태"),
                                fieldWithPath("data.zipcode").type(JsonFieldType.STRING)
                                        .description("우편번호"),
                                fieldWithPath("data.roadAddress").type(JsonFieldType.STRING)
                                        .description("도로명 주소"),
                                fieldWithPath("data.jibunAddress").type(JsonFieldType.STRING)
                                        .description("지번 주소"),
                                fieldWithPath("data.detailAddress").type(JsonFieldType.STRING)
                                        .description("상세 주소"),
                                fieldWithPath("data.phoneNumber").type(JsonFieldType.STRING)
                                        .description("연락처"),
                                fieldWithPath("data.createdDate").type(JsonFieldType.STRING)
                                        .description("작성일")
                        )
                ));
    }

    @DisplayName("유기동물 게시글 삭제 API")
    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteAbandonReport() throws Exception {

        Long abandonReportId = 1L;

        given(abandonReportService.deleteAbandonReport(anyLong(), anyString()))
                .willReturn(abandonReportId);

        mockMvc.perform(
                        delete("/abandon/report/{abandonReportId}", abandonReportId)
                                .header("Authentication", "authentication")
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andDo(document("delete-abandon-report",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("abandonReportId").description("유기동물 게시글 신고 PK")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.NUMBER)
                                        .description("삭제된 유기동물 게시글 신고 PK 값")
                        )
                ));
    }
}
