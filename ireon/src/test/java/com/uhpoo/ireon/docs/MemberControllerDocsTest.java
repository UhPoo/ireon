package com.uhpoo.ireon.docs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uhpoo.ireon.api.controller.member.MemberController;
import com.uhpoo.ireon.api.controller.member.request.MemberSignUpRequest;
import com.uhpoo.ireon.api.controller.member.response.MemberSignUpResponse;
import com.uhpoo.ireon.api.service.member.MemberService;
import com.uhpoo.ireon.api.service.member.dto.MemberSignUpDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberControllerDocsTest.class)
public class MemberControllerDocsTest extends RestDocsSupport{
    private final MemberService memberService = mock(MemberService.class);


    @Override
    protected Object initController() {
        return new MemberController(memberService);
    }

    @DisplayName("회원가입 API")
    @Test
    void signup() throws Exception {
        MemberSignUpRequest request = MemberSignUpRequest.builder()
                .email("email@email.com")
                .password("pwd")
                .nickname("nickname")
                .build();

        given(memberService.signup(any(MemberSignUpDto.class)))
                .willReturn(MemberSignUpResponse.builder()
                        .memberId(1L)
                        .email("email@email.com")
                        .nickname("nickname")
                        .build()
                );

        mockMvc.perform(
                post("/member/signup")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("member-signup",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER).description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING).description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("응답 데이터"),
                                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원식별키"),
                                fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                                fieldWithPath("data.nickname").type(JsonFieldType.STRING).description("닉네임")
                        )


                        ));
    }
}
