package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.abandon.AbandonScrapController;
import com.uhpoo.ireon.api.controller.abandon.request.AbandonScrapRequest;
import com.uhpoo.ireon.api.controller.abandon.response.AbandonResponse;
import com.uhpoo.ireon.api.service.abandon.AbandonQueryService;
import com.uhpoo.ireon.api.service.abandon.AbandonScrapService;
import com.uhpoo.ireon.api.service.abandon.dto.AbandonScrapDto;
import com.uhpoo.ireon.domain.abandon.AbandonStatus;
import com.uhpoo.ireon.domain.common.animal.AnimalType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AbandonScrapControllerDocsTest.class)
public class AbandonScrapControllerDocsTest extends RestDocsSupport {

    private final AbandonScrapService abandonScrapService = mock(AbandonScrapService.class);
    private final AbandonQueryService abandonQueryService = mock(AbandonQueryService.class);


    @Override
    protected Object initController() {
        return new AbandonScrapController(abandonScrapService, abandonQueryService);
    }

    @DisplayName("유기동물 게시글 등록 API")
    @Test
    @WithMockUser
    void createAbandon() throws Exception {

        AbandonScrapRequest request = AbandonScrapRequest.builder()
                .abandonId(1L)
                .build();

        given(abandonScrapService.scrap(any(AbandonScrapDto.class), anyString()))
                .willReturn(1L);

        mockMvc.perform(
                        post("/abandon/scrap")
                                .header("Authentication", "authentication")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("abandon-scrap",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("abandonId").type(JsonFieldType.NUMBER)
                                        .description("유기동물 게시글 PK")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.NUMBER)
                                        .description("등록/취소된 유기동물 스크랩 PK 값")
                        )
                ));
    }

    @DisplayName("스크랩한 유기동물 전체 조회 API")
    @Test
    @WithMockUser
    void getScrappedAbandons() throws Exception {

        AbandonResponse item1 = AbandonResponse.builder()
                .abandonId(3L)
                .title("제목1")
                .author("작성자1")
                .animalType(AnimalType.DOG)
                .abandonStatus(AbandonStatus.SEARCHING)
                .roadAddress("서울시 송파구 토성로")
                .jibunAddress("서울시 송파구 풍납동")
                .detailAddress("비밀")
                .phoneNumber("010-1234-5678")
                .clipped(true)
                .createdDate(LocalDateTime.of(2024, 6, 27, 0, 0, 0))
                .build();

        AbandonResponse item2 = AbandonResponse.builder()
                .abandonId(1L)
                .title("제목2")
                .author("작성자2")
                .animalType(AnimalType.CAT)
                .abandonStatus(AbandonStatus.PROTECTING)
                .roadAddress("서울시 송파구 토성로")
                .jibunAddress("서울시 송파구 풍납동")
                .detailAddress("비밀")
                .phoneNumber("010-1234-5678")
                .clipped(true)
                .createdDate(LocalDateTime.of(2024, 6, 26, 0, 0, 0))
                .build();

        List<AbandonResponse> items = List.of(item1, item2);

        PageResponse<List<AbandonResponse>> response = PageResponse.of(false, items);

        given(abandonQueryService.getScrappedAbandons(anyString()))
                .willReturn(response);

        mockMvc.perform(
                        get("/abandon/scrap")
                                .header("Authentication", "authentication")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-scrapped-abandons",
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
                                fieldWithPath("data.items[].clipped").type(JsonFieldType.BOOLEAN)
                                        .description("스크랩 여부"),
                                fieldWithPath("data.items[].createdDate").type(JsonFieldType.STRING)
                                        .description("작성일")
                        )
                ));

    }
}
