
package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.controller.lost.LostController;
import com.uhpoo.ireon.api.controller.lost.request.CreateLostRequest;
import com.uhpoo.ireon.api.service.lost.LostService;
import com.uhpoo.ireon.api.service.lost.dto.CreateLostDto;
import com.uhpoo.ireon.domain.common.animal.AnimalType;
import com.uhpoo.ireon.domain.common.animal.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.multipart;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LostControllerDocsTest.class)
public class LostControllerDocsTest extends RestDocsSupport  {

    private final LostService lostService = mock(LostService.class);

    @Override
    protected Object initController() {
        return new LostController(lostService);
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
}
