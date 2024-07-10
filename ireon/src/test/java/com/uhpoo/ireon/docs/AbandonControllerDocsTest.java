package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.abandon.AbandonController;
import com.uhpoo.ireon.api.controller.abandon.request.CreateAbandonRequest;
import com.uhpoo.ireon.api.controller.abandon.request.EditAbandonRequest;
import com.uhpoo.ireon.api.controller.abandon.response.AbandonDetailResponse;
import com.uhpoo.ireon.api.controller.abandon.response.AbandonResponse;
import com.uhpoo.ireon.api.service.abandon.command.AbandonService;
import com.uhpoo.ireon.api.service.abandon.dto.CreateAbandonDto;
import com.uhpoo.ireon.api.service.abandon.dto.EditAbandonDto;
import com.uhpoo.ireon.api.service.abandon.query.AbandonQueryService;
import com.uhpoo.ireon.domain.abandon.AbandonStatus;
import com.uhpoo.ireon.domain.abandon.VaccinationStatus;
import com.uhpoo.ireon.domain.abandon.dto.AbandonDetailDto;
import com.uhpoo.ireon.domain.abandon.dto.SearchCondition;
import com.uhpoo.ireon.domain.common.DeSexing;
import com.uhpoo.ireon.domain.common.animal.AnimalType;
import com.uhpoo.ireon.domain.common.animal.Gender;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AbandonControllerDocsTest.class)
public class AbandonControllerDocsTest extends RestDocsSupport {

    private final AbandonService abandonService = mock(AbandonService.class);
    private final AbandonQueryService abandonQueryService = mock(AbandonQueryService.class);

    @Override
    protected Object initController() {
        return new AbandonController(abandonService, abandonQueryService);
    }

    @DisplayName("유기동물 게시글 등록 API")
    @Test
    @WithMockUser
    void createAbandon() throws Exception {
        CreateAbandonRequest request = CreateAbandonRequest.builder()
                .title("입양해가실분")
                .content("찾아용")
                .animalType(AnimalType.DOG.getText())
                .animalDetail("믹스견")
                .animalGender(Gender.MALE.getText())
                .animalAge(3)
                .vaccinationStatus(VaccinationStatus.FIRST.getText())
                .deSexing("미완료")
                .abandonStatus(AbandonStatus.SEARCHING.getText())
                .zipcode("11111")
                .roadAddress("서울시 송파구 토성로")
                .jibunAddress("서울시 송파구 풍납동")
                .detailAddress("비밀")
                .phoneNumber("010-1234-5678")
                .build();

        MockMultipartFile file = new MockMultipartFile("file", "image.jpg",
                MediaType.IMAGE_JPEG_VALUE, "image data".getBytes());

        String jsonRequest = objectMapper.writeValueAsString(request);
        MockMultipartFile jsonRequestPart = new MockMultipartFile("request", "request.json",
                APPLICATION_JSON_VALUE, jsonRequest.getBytes(UTF_8));

        given(abandonService.createAbandon(any(CreateAbandonDto.class), anyString(), any(MultipartFile.class)))
                .willReturn(1L);

        mockMvc.perform(
                        multipart("/abandon")
                                .file(file)
                                .file(jsonRequestPart)
                                .header("Authentication", "authentication")
                                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("create-abandon",
                        preprocessResponse(prettyPrint()),
                        requestParts(
                                partWithName("file").description("첨부파일"),
                                partWithName("request").description("유기동물 게시글 정보")
                        ),
                        requestPartFields("request",
                                fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("게시글 제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING)
                                        .description("게시글 내용"),
                                fieldWithPath("animalType").type(JsonFieldType.STRING)
                                        .description("동물 종류"),
                                fieldWithPath("animalDetail").type(JsonFieldType.STRING)
                                        .description("세부 동물 종류"),
                                fieldWithPath("animalGender").type(JsonFieldType.STRING)
                                        .description("동물 성별"),
                                fieldWithPath("animalAge").type(JsonFieldType.NUMBER)
                                        .description("동물 나이"),
                                fieldWithPath("vaccinationStatus").type(JsonFieldType.STRING)
                                        .description("접종 상태"),
                                fieldWithPath("deSexing").type(JsonFieldType.STRING)
                                        .description("중성화 여부"),
                                fieldWithPath("abandonStatus").type(JsonFieldType.STRING)
                                        .description("유기동물 상태"),
                                fieldWithPath("zipcode").type(JsonFieldType.STRING)
                                        .description("우편번호"),
                                fieldWithPath("roadAddress").type(JsonFieldType.STRING)
                                        .description("도로명 주소"),
                                fieldWithPath("jibunAddress").type(JsonFieldType.STRING)
                                        .description("지번 주소"),
                                fieldWithPath("detailAddress").type(JsonFieldType.STRING)
                                        .description("상세 주소"),
                                fieldWithPath("phoneNumber").type(JsonFieldType.STRING)
                                        .description("연락처")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.NUMBER)
                                        .description("등록된 유기동물 게시글 PK 값")
                        )
                ));

    }

    @DisplayName("유기동물 전체 조회 API")
    @Test
    @WithMockUser
    void getAbandons() throws Exception {

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
                .thumbnailUrl("IMG_URL")
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
                .clipped(false)
                .thumbnailUrl("IMG_URL")
                .createdDate(LocalDateTime.of(2024, 6, 26, 0, 0, 0))
                .build();

        List<AbandonResponse> items = List.of(item1, item2);

        PageResponse<List<AbandonResponse>> response = PageResponse.of(false, items);

        given(abandonQueryService.getAbandons(any(SearchCondition.class), anyLong(), anyString()))
                .willReturn(response);

        mockMvc.perform(
                        get("/abandon")
                                .header("Authentication", "authentication")
                                .param("keyword", "keyword")
                                .param("lastAbandonId", "")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-abandons",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        queryParameters(
                                parameterWithName("keyword").description("검색어"),
                                parameterWithName("lastAbandonId").description("마지막으로 조회된 유기동물 게시글 PK")
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
                                fieldWithPath("data.items[].thumbnailUrl").type(JsonFieldType.STRING)
                                        .description("썸네일 이미지 저장 경로"),
                                fieldWithPath("data.items[].createdDate").type(JsonFieldType.STRING)
                                        .description("작성일")
                        )
                ));

    }

    @DisplayName("유기동물 상세 조회 API")
    @Test
    @WithMockUser
    void getAbandon() throws Exception {

        AbandonDetailDto dto = AbandonDetailDto.builder()
                .abandonId(1L)
                .title("제목1")
                .author("작성자1")
                .animalType("개")
                .animalDetail("말티즈")
                .animalGender("암컷")
                .animalAge(5)
                .vaccinationStatus(VaccinationStatus.SECOND.getText())
                .deSexing(DeSexing.UNDONE)
                .abandonStatus(AbandonStatus.SEARCHING.getText())
                .zipcode("11111")
                .roadAddress("서울시 송파구 토성로")
                .jibunAddress("서울시 송파구 풍납동")
                .detailAddress("비밀")
                .phoneNumber("010-1234-5678")
                .clipped(true)
                .createdDate("2024-03-05")
                .build();

        List<String> attachments = List.of("URL1", "URL2");

        AbandonDetailResponse response = AbandonDetailResponse.builder()
                .dto(dto)
                .attachments(attachments)
                .build();

        given(abandonQueryService.getAbandon(anyLong(), anyString()))
                .willReturn(response);

        mockMvc.perform(
                        get("/abandon/{abandonId}", dto.getAbandonId())
                                .header("Authentication", "authentication")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-abandon",
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
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("유기동물 상세 조회 결과"),
                                fieldWithPath("data.dto").type(JsonFieldType.OBJECT)
                                        .description("유기동물 상세 조회 게시글 정보"),
                                fieldWithPath("data.attachments").type(JsonFieldType.ARRAY)
                                        .description("유기동물 게시글 첨부파일 URL 리스트"),
                                fieldWithPath("data.dto.abandonId").type(JsonFieldType.NUMBER)
                                        .description("유기동물 게시글 PK"),
                                fieldWithPath("data.dto.title").type(JsonFieldType.STRING)
                                        .description("글 제목"),
                                fieldWithPath("data.dto.author").type(JsonFieldType.STRING)
                                        .description("작성자"),
                                fieldWithPath("data.dto.animalType").type(JsonFieldType.STRING)
                                        .description("동물 종류"),
                                fieldWithPath("data.dto.animalDetail").type(JsonFieldType.STRING)
                                        .description("동물 종류 상세"),
                                fieldWithPath("data.dto.animalGender").type(JsonFieldType.STRING)
                                        .description("동물 성별"),
                                fieldWithPath("data.dto.animalAge").type(JsonFieldType.NUMBER)
                                        .description("동물 나이"),
                                fieldWithPath("data.dto.vaccinationStatus").type(JsonFieldType.STRING)
                                        .description("예방접종 상태"),
                                fieldWithPath("data.dto.deSexing").type(JsonFieldType.STRING)
                                        .description("중성화 여부"),
                                fieldWithPath("data.dto.abandonStatus").type(JsonFieldType.STRING)
                                        .description("유기동물 상태"),
                                fieldWithPath("data.dto.zipcode").type(JsonFieldType.STRING)
                                        .description("우편번호"),
                                fieldWithPath("data.dto.roadAddress").type(JsonFieldType.STRING)
                                        .description("도로명 주소"),
                                fieldWithPath("data.dto.jibunAddress").type(JsonFieldType.STRING)
                                        .description("지번 주소"),
                                fieldWithPath("data.dto.detailAddress").type(JsonFieldType.STRING)
                                        .description("상세 주소"),
                                fieldWithPath("data.dto.phoneNumber").type(JsonFieldType.STRING)
                                        .description("연락처"),
                                fieldWithPath("data.dto.clipped").type(JsonFieldType.BOOLEAN)
                                        .description("스크랩 여부"),
                                fieldWithPath("data.dto.createdDate").type(JsonFieldType.STRING)
                                        .description("작성일")
                        )
                ));

    }

    @DisplayName("유기동물 수정 API")
    @Test
    @WithMockUser
    void editAbandon() throws Exception {

        Long abandonId = 1L;

        EditAbandonRequest request = EditAbandonRequest.builder()
                .title("입양해가실분")
                .content("찾아용")
                .animalType(AnimalType.DOG.getText())
                .animalDetail("믹스견")
                .animalGender(Gender.MALE.getText())
                .age(3)
                .vaccinationStatus(VaccinationStatus.FIRST.getText())
                .neutralized(true)
                .abandonStatus(AbandonStatus.SEARCHING.getText())
                .zipcode("11111")
                .road("서울시 송파구 토성로")
                .jibun("서울시 송파구 풍납동")
                .detail("비밀")
                .phoneNumber("010-1234-5678")
                .build();

        given(abandonService.editAbandon(any(EditAbandonDto.class), anyString(), any(MultipartFile.class)))
                .willReturn(abandonId);

        MockMultipartFile file = new MockMultipartFile("file", "image.jpg",
                MediaType.IMAGE_JPEG_VALUE, "image data".getBytes());

        String jsonRequest = objectMapper.writeValueAsString(request);
        MockMultipartFile jsonRequestPart = new MockMultipartFile("request", "request.json",
                APPLICATION_JSON_VALUE, jsonRequest.getBytes(UTF_8));

        // multipart 는 기본적으로 POST 요청을 위한 처리로만 사용되므로 다음과 같이 Override 해서 요청을 만들어줘야함
        MockMultipartHttpServletRequestBuilder builder =
                RestDocumentationRequestBuilders.
                        multipart("/abandon/{abandonId}", abandonId);

        builder.with(new RequestPostProcessor() {
            @Override
            public @NotNull MockHttpServletRequest postProcessRequest(@NotNull MockHttpServletRequest request) {
                request.setMethod("PATCH");
                return request;
            }
        });

        mockMvc.perform(
                        builder
                                .file(file)
                                .file(jsonRequestPart)
                                .header("Authentication", "authentication")
                                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("edit-abandon",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("abandonId").description("유기동물 게시글 PK")
                        ),
                        requestParts(
                                partWithName("file").description("유기동물 이미지"),
                                partWithName("request").description("유기동물 게시글 정보")
                        ),
                        requestPartFields("request",
                                fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("게시글 제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING)
                                        .description("게시글 내용"),
                                fieldWithPath("animalType").type(JsonFieldType.STRING)
                                        .description("동물 종류"),
                                fieldWithPath("animalDetail").type(JsonFieldType.STRING)
                                        .description("세부 동물 종류"),
                                fieldWithPath("animalGender").type(JsonFieldType.STRING)
                                        .description("동물 성별"),
                                fieldWithPath("age").type(JsonFieldType.NUMBER)
                                        .description("동물 나이"),
                                fieldWithPath("vaccinationStatus").type(JsonFieldType.STRING)
                                        .description("접종 상태"),
                                fieldWithPath("neutralized").type(JsonFieldType.BOOLEAN)
                                        .description("중성화 여부"),
                                fieldWithPath("abandonStatus").type(JsonFieldType.STRING)
                                        .description("유기동물 상태"),
                                fieldWithPath("zipcode").type(JsonFieldType.STRING)
                                        .description("우편번호"),
                                fieldWithPath("road").type(JsonFieldType.STRING)
                                        .description("도로명 주소"),
                                fieldWithPath("jibun").type(JsonFieldType.STRING)
                                        .description("지번 주소"),
                                fieldWithPath("detail").type(JsonFieldType.STRING)
                                        .description("상세 주소"),
                                fieldWithPath("phoneNumber").type(JsonFieldType.STRING)
                                        .description("연락처")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.NUMBER)
                                        .description("수정된 유기동물 게시글 PK 값")
                        )
                ));
    }

    @DisplayName("유기동물 게시글 삭제 API")
    @Test
    @WithMockUser
    void deleteAbandon() throws Exception {

        Long abandonId = 1L;

        given(abandonService.deleteAbandon(anyLong(), anyString()))
                .willReturn(abandonId);

        mockMvc.perform(
                        delete("/abandon/{abandonId}", abandonId)
                                .header("Authentication", "authentication")
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andDo(document("delete-abandon",
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
                                        .description("삭제된 유기동물 게시글 PK 값")
                        )
                ));
    }
}
