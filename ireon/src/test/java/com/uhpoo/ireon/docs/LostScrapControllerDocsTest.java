package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.controller.lost.LostScrapController;
import com.uhpoo.ireon.api.controller.lost.request.AddLostScrapRequest;
import com.uhpoo.ireon.api.service.lost.LostScrapService;
import com.uhpoo.ireon.api.service.lost.dto.AddLostScrapDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
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
    private final LostScrapController   lostScrapController = mock(LostScrapController.class);

    @Override
    protected Object initController() {
        return new LostScrapController(lostScrapService);
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

}
