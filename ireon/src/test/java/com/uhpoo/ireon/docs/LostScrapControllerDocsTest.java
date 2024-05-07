package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.lost.LostScrapController;
import com.uhpoo.ireon.api.controller.lost.request.AddLostScrapRequest;
import com.uhpoo.ireon.api.controller.lost.response.LostResponse;
import com.uhpoo.ireon.api.service.lost.LostQueryService;
import com.uhpoo.ireon.api.service.lost.LostScrapService;
import com.uhpoo.ireon.api.service.lost.dto.AddLostScrapDto;
import com.uhpoo.ireon.domain.lost.LostStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 실종동물 스크랩 APIDocs 테스트
 *
 * @author yekki
 */
@WebMvcTest(LostScrapControllerDocsTest.class)
public class LostScrapControllerDocsTest extends RestDocsSupport  {
    private final LostScrapService  lostScrapService = mock(LostScrapService.class);
    private final LostQueryService lostQueryService = mock(LostQueryService.class);
    private final LostScrapController   lostScrapController = mock(LostScrapController.class);

    @Override
    protected Object initController() {
        return new LostScrapController(lostScrapService, lostQueryService);
    }

    @DisplayName("실종동물 스크랩 추가 API")
    @Test
    @WithMockUser
    void addLostScrap() throws Exception{
        AddLostScrapRequest request = AddLostScrapRequest.builder()
                .lostId(1L)
                .build();


        given(lostScrapService.addLostScrap(any(AddLostScrapDto.class)))
                .willReturn(1L);

        mockMvc.perform(
                        post("/lost/scrap")
                                .header("Authentication", "authentication")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("add-lost-scrap",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("lostId").type(JsonFieldType.NUMBER)
                                        .description("실종동물 게시글 PK")
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

    @DisplayName("스크랩한 실종동물 전체 조회 API")
    @Test
    @WithMockUser
    void getLostScraps() throws Exception {

        LostResponse item1 = LostResponse.builder()
                .LostId(3L)
                .title("멍멍이 찾아요")
                .author("애타는 주인")
                .animalType("개")
                .lostStatus(LostStatus.LOST.getText())
                .latitude(BigDecimal.valueOf(37.576987703009536))
                .longitude(BigDecimal.valueOf(126.98023424093205))
                .phoneNumber("010-1234-5678")
                .clipped(true)
                .createdDate("2024-04-01")
                .build();

        LostResponse item2 = LostResponse.builder()
                .LostId(1L)
                .title("멍멍이 봤어요")
                .author("선랑햔 시민")
                .animalType("개")
                .lostStatus(LostStatus.DISCOVERED.getText())
                .latitude(BigDecimal.valueOf(33.576987703009536))
                .longitude(BigDecimal.valueOf(126.98023424093205))
                .phoneNumber("011-8765-4321")
                .clipped(true)
                .createdDate("2024-04-02")
                .build();

        List<LostResponse> items = List.of(item1, item2);

        PageResponse<List<LostResponse>> response = PageResponse.of(false, items);

        given(lostQueryService.getLostScraps(anyString()))
                .willReturn(response);

        mockMvc.perform(
                        get("/lost/scrap")
                                .header("Authentication", "authentication")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-lost-scraps",
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
                                fieldWithPath("data.items[].lostId").type(JsonFieldType.NUMBER)
                                        .description("실종동물 게시글 PK"),
                                fieldWithPath("data.items[].title").type(JsonFieldType.STRING)
                                        .description("글 제목"),
                                fieldWithPath("data.items[].author").type(JsonFieldType.STRING)
                                        .description("작성자"),
                                fieldWithPath("data.items[].animalType").type(JsonFieldType.STRING)
                                        .description("동물 종류"),
                                fieldWithPath("data.items[].lostStatus").type(JsonFieldType.STRING)
                                        .description("실종동물 상태"),
                                fieldWithPath("data.items[].latitude").type(JsonFieldType.NUMBER)
                                        .description("발견 위도"),
                                fieldWithPath("data.items[].longitude").type(JsonFieldType.NUMBER)
                                        .description("발견 경도"),
                                fieldWithPath("data.items[].phoneNumber").type(JsonFieldType.STRING)
                                        .description("연락처"),
                                fieldWithPath("data.items[].clipped").type(JsonFieldType.BOOLEAN)
                                        .description("스크랩 여부"),
                                fieldWithPath("data.items[].createdDate").type(JsonFieldType.STRING)
                                        .description("작성일")
                        )
                ));
    }
}
