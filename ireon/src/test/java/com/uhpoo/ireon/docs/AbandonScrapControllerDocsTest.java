package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.controller.abandon.AbandonScrapController;
import com.uhpoo.ireon.api.controller.abandon.request.AbandonScrapRequest;
import com.uhpoo.ireon.api.service.abandon.AbandonScrapService;
import com.uhpoo.ireon.api.service.abandon.dto.AbandonScrapDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AbandonScrapControllerDocsTest.class)
public class AbandonScrapControllerDocsTest extends RestDocsSupport {

    private final AbandonScrapService abandonScrapService = mock(AbandonScrapService.class);


    @Override
    protected Object initController() {
        return new AbandonScrapController(abandonScrapService);
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
}
