package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.controller.abandon.AbandonController;
import com.uhpoo.ireon.api.controller.abandon.request.CreateAbandonRequest;
import com.uhpoo.ireon.api.service.abandon.AbandonService;
import com.uhpoo.ireon.api.service.abandon.dto.CreateAbandonDto;
import com.uhpoo.ireon.domain.abandon.AnimalType;
import com.uhpoo.ireon.domain.abandon.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.multipart;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.partWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AbandonControllerDocsTest.class)
public class AbandonControllerDocsTest extends RestDocsSupport {

    private final AbandonService abandonService = mock(AbandonService.class);

    @Override
    protected Object initController() {
        return new AbandonController(abandonService);
    }

    @DisplayName("유기동물 게시글 등록 API")
    @Test
    @WithMockUser
    void createAbandon() throws Exception {
        CreateAbandonRequest request = CreateAbandonRequest.builder()
                .title("강아지 잃어버리신분")
                .content("찾아용")
                .animalType(AnimalType.DOG.getText())
                .animalDetail("믹스견")
                .animalGender(Gender.MALE.getText())
                .foundTime(LocalDateTime.now().toString())
                .foundLoc("서울시 송파구")
                .currentLoc("서울시 송파구")
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
                                fieldWithPath("foundTime").type(JsonFieldType.STRING)
                                        .description("발견시간"),
                                fieldWithPath("foundLoc").type(JsonFieldType.STRING)
                                        .description("발견위치"),
                                fieldWithPath("currentLoc").type(JsonFieldType.STRING)
                                        .description("현재위치"),
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
