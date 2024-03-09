package com.uhpoo.ireon.docs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uhpoo.ireon.api.controller.member.MemberController;
import com.uhpoo.ireon.api.controller.member.request.MemberLoginRequest;
import com.uhpoo.ireon.api.controller.member.request.MemberSignUpRequest;
import com.uhpoo.ireon.api.controller.member.response.MemberSignUpResponse;
import com.uhpoo.ireon.api.controller.member.response.TokenResponse;
import com.uhpoo.ireon.api.service.member.MemberService;
import com.uhpoo.ireon.api.service.member.dto.MemberLoginDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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

    @DisplayName("로그인 API")
    @Test
    void login() throws Exception {
        MemberLoginRequest request = MemberLoginRequest.builder()
                        .email("email@email.com")
                        .password("pwd")
                        .build();

        given(memberService.login(any(MemberLoginDto.class)))
                .willReturn(TokenResponse.builder()
                        .email("email@email.com")
                        .nickname("nickname")
                        .grantType("bearer")
                        .accessToken("accessToken")
                        .refreshToken("refreshToken")
                        .accessTokenExpiresIn(151690L)
                        .build()
                );

        mockMvc.perform(
                        post("/member/login")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("member-login",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER).description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING).description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("응답 데이터"),
                                fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                                fieldWithPath("data.nickname").type(JsonFieldType.STRING).description("닉네임"),
                                fieldWithPath("data.grantType").type(JsonFieldType.STRING).description("Grant Type"),
                                fieldWithPath("data.accessToken").type(JsonFieldType.STRING).description("Access Token"),
                                fieldWithPath("data.refreshToken").type(JsonFieldType.STRING).description("Refresh Token"),
                                fieldWithPath("data.accessTokenExpiresIn").type(JsonFieldType.NUMBER).description("Expires Time")
                        )


                ));
    }

    @DisplayName("로그아웃 API")
    @Test
    void logout() throws Exception {

        mockMvc.perform(
                        patch("/member/logout")
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(document("member-logout",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER).description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING).description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.NULL).description("응답 데이터")
                        )


                ));
    }
}
