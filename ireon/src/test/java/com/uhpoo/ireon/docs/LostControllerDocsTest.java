
package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.PageResponse;
import com.uhpoo.ireon.api.controller.lost.LostController;
import com.uhpoo.ireon.api.controller.lost.request.CreateLostRequest;
import com.uhpoo.ireon.api.controller.lost.request.EditLostRequest;
import com.uhpoo.ireon.api.controller.lost.response.LostDetailResponse;
import com.uhpoo.ireon.api.controller.lost.response.LostResponse;
import com.uhpoo.ireon.api.service.lost.LostQueryService;
import com.uhpoo.ireon.api.service.lost.LostService;
import com.uhpoo.ireon.api.service.lost.dto.CreateLostDto;
import com.uhpoo.ireon.api.service.lost.dto.EditLostDto;
import com.uhpoo.ireon.domain.common.DeSexing;
import com.uhpoo.ireon.domain.common.animal.AnimalType;
import com.uhpoo.ireon.domain.common.animal.Gender;
import com.uhpoo.ireon.domain.lost.LostStatus;
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

import java.math.BigDecimal;
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

@WebMvcTest(LostControllerDocsTest.class)
public class LostControllerDocsTest extends RestDocsSupport  {

    private final LostService lostService = mock(LostService.class);
    private final LostQueryService lostQueryService = mock(LostQueryService.class);

    @Override
    protected Object initController() {
        return new LostController(lostService, lostQueryService);
    }

    @DisplayName("실종동물 게시글 등록 API")
    @Test
    @WithMockUser
    void createLost() throws Exception {
        CreateLostRequest request = CreateLostRequest.builder()
                .title("믹스견을 찾습니다.")
                .content("며칠에 어디서 어쩌구 잃어버렸어요")
                .animalType(AnimalType.DOG.getText())
                .animalDetail("곰상 믹스견")
                .animalGender(Gender.FEMALE.getText())
                .animalAge(3)
                .deSexing(DeSexing.DONE.getText())
                .lostStatus("")
                .zipcode("03045")
                .roadAddress("서울 종로구 사직로 161")
                .jibunAddress("서울 종로구 세종로 1-1")
                .detailAddress("경복궁")
                .latitude(BigDecimal.valueOf(37.576987703009536))
                .longitude(BigDecimal.valueOf(126.98023424093205))
                .phoneNumber("010-1234-5678")
                .build();

        MockMultipartFile file = new MockMultipartFile("file", "image.jpg",
                MediaType.IMAGE_JPEG_VALUE, "image data".getBytes());

        String jsonRequest = objectMapper.writeValueAsString(request);
        MockMultipartFile jsonRequestPart = new MockMultipartFile("request", "request.json",
                APPLICATION_JSON_VALUE, jsonRequest.getBytes(UTF_8));

        given(lostService.createLost(any(CreateLostDto.class), anyString(), any(MultipartFile.class)))
                .willReturn(1L);

        mockMvc.perform(
                        multipart("/lost")
                                .file(file)
                                .file(jsonRequestPart)
                                .header("Authentication", "authentication")
                                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("create-lost",
                        preprocessResponse(prettyPrint()),
                        requestParts(
                                partWithName("file").description("첨부파일"),
                                partWithName("request").description("실종동물 게시글 정보")
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
                                fieldWithPath("neutralized").type(JsonFieldType.BOOLEAN)
                                        .description("중성화 여부"),
                                fieldWithPath("lostStatus").type(JsonFieldType.STRING)
                                        .description("실종동물 상태"),
                                fieldWithPath("zipcode").type(JsonFieldType.STRING)
                                        .description("우편번호"),
                                fieldWithPath("roadAddress").type(JsonFieldType.STRING)
                                        .description("도로명 주소"),
                                fieldWithPath("jibunAddress").type(JsonFieldType.STRING)
                                        .description("지번 주소"),
                                fieldWithPath("detailAddress").type(JsonFieldType.STRING)
                                        .description("상세 주소"),
                                fieldWithPath("latitude").type(JsonFieldType.NUMBER)
                                        .description("발견 위도"),
                                fieldWithPath("longitude").type(JsonFieldType.NUMBER)
                                        .description("발견 경도"),
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
                                        .description("등록된 PK 값")
                        )
                ));

    }

    @DisplayName("실종동물 게시글 수정 API")
    @Test
    @WithMockUser
    void editLost() throws Exception {

        Long lostId = 1L;

        EditLostRequest request = EditLostRequest.builder()
                .title("(수정)믹스견을 찾습니다.")
                .content("며칠에 어디서 어쩌구 잃어버렸어요. +어쩌구는 저쩌시 어쩌구예요.")
                .animalType(AnimalType.DOG.getText())
                .animalDetail("곰상 믹스견 + 갈색털")
                .animalGender(Gender.FEMALE.getText())
                .animalAge(3)
                .neutralized(true)
                .lostStatus("")
                .zipcode("03045")
                .roadAddress("서울 종로구 사직로 161")
                .jibunAddress("서울 종로구 세종로 1-1")
                .detailAddress("경복궁")
                .latitude(BigDecimal.valueOf(37.576987703009536))
                .longitude(BigDecimal.valueOf(126.98023424093205))
                .phoneNumber("010-1234-5678")
                .build();

        MockMultipartFile file = new MockMultipartFile("file", "image.jpg",
                MediaType.IMAGE_JPEG_VALUE, "image data".getBytes());

        String jsonRequest = objectMapper.writeValueAsString(request);
        MockMultipartFile jsonRequestPart = new MockMultipartFile("request", "request.json",
                APPLICATION_JSON_VALUE, jsonRequest.getBytes(UTF_8));

        given(lostService.editLost(any(EditLostDto.class), anyString(), any(MultipartFile.class)))
                .willReturn(lostId);

        //PATCH용 overriding
        MockMultipartHttpServletRequestBuilder builder =
                RestDocumentationRequestBuilders.multipart("/lost/{lostId}", lostId);
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
                .andDo(document("edit-lost",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("lostId").description("실종동물 게시글 PK")
                        ),
                        requestParts(
                                partWithName("file").description("첨부파일"),
                                partWithName("request").description("실종동물 게시글 정보")
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
                                fieldWithPath("neutralized").type(JsonFieldType.BOOLEAN)
                                        .description("중성화 여부"),
                                fieldWithPath("lostStatus").type(JsonFieldType.STRING)
                                        .description("실종동물 상태"),
                                fieldWithPath("zipcode").type(JsonFieldType.STRING)
                                        .description("우편번호"),
                                fieldWithPath("roadAddress").type(JsonFieldType.STRING)
                                        .description("도로명 주소"),
                                fieldWithPath("jibunAddress").type(JsonFieldType.STRING)
                                        .description("지번 주소"),
                                fieldWithPath("detailAddress").type(JsonFieldType.STRING)
                                        .description("상세 주소"),
                                fieldWithPath("latitude").type(JsonFieldType.NUMBER)
                                        .description("발견 위도"),
                                fieldWithPath("longitude").type(JsonFieldType.NUMBER)
                                        .description("발견 경도"),
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
                                        .description("수정 된 PK 값")
                        )
                ));

    }

    @DisplayName("실종동물 게시글 삭제 API")
    @Test
    @WithMockUser
    void deleteLost() throws Exception {
        Long lostId = 1L;

        given(lostService.deleteLost(anyLong(), anyString()))
                .willReturn(lostId);

        mockMvc.perform(
                        delete("/lost/{lostId}", lostId)
                                .header("Authentication", "authentication")
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andDo(document("delete-lost",
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
                                        .description("삭제된 실종동물 게시글 PK 값")
                        )
                ));
    }

    @DisplayName("실종동물 전체 조회 API")
    @Test
    @WithMockUser
    void getLosts() throws Exception {

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

        LostResponse item3 = LostResponse.builder()
                .LostId(2L)
                .title("고양이 임보중이에요")
                .author("고양이 세이버")
                .animalType("고양이")
                .lostStatus(LostStatus.PROTECTING.getText())
                .latitude(BigDecimal.valueOf(31.576987703009536))
                .longitude(BigDecimal.valueOf(126.98023424093205))
                .phoneNumber("010-8282-2828")
                .clipped(false)
                .createdDate("2024-04-15")
                .build();

        List<LostResponse> items = List.of(item1, item2, item3);

        PageResponse<List<LostResponse>> response = PageResponse.of(false, items);

        given(lostQueryService.getLosts())
                .willReturn(response);

        mockMvc.perform(
                        get("/lost")
                                .header("Authentication", "authentication")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-losts",
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

    @DisplayName("실종동물 상세 조회 API")
    @Test
    @WithMockUser
    void getLost() throws Exception {

        LostDetailResponse response = LostDetailResponse.builder()
                .lostId(1L)
                .title("멍멍이 봤어요")
                .author("선량한 작성자")
                .content("경복궁에서 멍멍이 봤어요")
                .animalType("개")
                .animalDetail("포메")
                .animalGender("암컷")
                .animalAge(0)
                .neutralized(false)
                .lostStatus(LostStatus.DISCOVERED.getText())
                .zipcode("03045")
                .roadAddress("서울 종로구 사직로 161")
                .jibunAddress("서울 종로구 세종로 1-1")
                .detailAddress("경복궁")
                .latitude(BigDecimal.valueOf(37.576987703009536))
                .longitude(BigDecimal.valueOf(126.98023424093205))
                .phoneNumber("010-8765-4321")
                .clipped(true)
                .createdDate("2024-04-02")
                .build();


        given(lostQueryService.getLost(anyLong()))
                .willReturn(response);

        mockMvc.perform(
                        get("/lost/{lostId}", response.getLostId())
                                .header("Authentication", "authentication")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-lost",
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
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("실종동물 상세 조회 결과"),
                                fieldWithPath("data.lostId").type(JsonFieldType.NUMBER)
                                        .description("실종동물 게시글 PK"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING)
                                        .description("글 제목"),
                                fieldWithPath("data.author").type(JsonFieldType.STRING)
                                        .description("작성자"),
                                fieldWithPath("data.content").type(JsonFieldType.STRING)
                                        .description("게시글 내용"),
                                fieldWithPath("data.animalType").type(JsonFieldType.STRING)
                                        .description("동물 종류"),
                                fieldWithPath("data.animalDetail").type(JsonFieldType.STRING)
                                        .description("동물 종류 상세"),
                                fieldWithPath("data.animalGender").type(JsonFieldType.STRING)
                                        .description("동물 성별"),
                                fieldWithPath("data.animalAge").type(JsonFieldType.NUMBER)
                                        .description("동물 나이"),
                                fieldWithPath("data.neutralized").type(JsonFieldType.BOOLEAN)
                                        .description("중성화 여부"),
                                fieldWithPath("data.lostStatus").type(JsonFieldType.STRING)
                                        .description("실종동물 상태"),
                                fieldWithPath("data.zipcode").type(JsonFieldType.STRING)
                                        .description("우편번호"),
                                fieldWithPath("data.roadAddress").type(JsonFieldType.STRING)
                                        .description("도로명 주소"),
                                fieldWithPath("data.jibunAddress").type(JsonFieldType.STRING)
                                        .description("지번 주소"),
                                fieldWithPath("data.detailAddress").type(JsonFieldType.STRING)
                                        .description("상세 주소"),
                                fieldWithPath("data.latitude").type(JsonFieldType.NUMBER)
                                        .description("발견 위도"),
                                fieldWithPath("data.longitude").type(JsonFieldType.NUMBER)
                                        .description("발견 경도"),
                                fieldWithPath("data.phoneNumber").type(JsonFieldType.STRING)
                                        .description("연락처"),
                                fieldWithPath("data.clipped").type(JsonFieldType.BOOLEAN)
                                        .description("스크랩 여부"),
                                fieldWithPath("data.createdDate").type(JsonFieldType.STRING)
                                        .description("작성일")
                        )
                ));

    }
}
