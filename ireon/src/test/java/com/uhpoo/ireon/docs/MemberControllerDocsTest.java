package com.uhpoo.ireon.docs;

import com.uhpoo.ireon.api.controller.member.MemberController;
import com.uhpoo.ireon.api.controller.member.request.MemberLoginRequest;
import com.uhpoo.ireon.api.controller.member.request.MemberSignUpRequest;
import com.uhpoo.ireon.api.controller.member.request.MemberUpdateRequest;
import com.uhpoo.ireon.api.controller.member.response.MemberResponse;
import com.uhpoo.ireon.api.controller.member.response.MemberSignUpResponse;
import com.uhpoo.ireon.api.controller.member.response.TokenResponse;
import com.uhpoo.ireon.api.service.member.MemberService;
import com.uhpoo.ireon.api.service.member.dto.MemberLoginDto;
import com.uhpoo.ireon.api.service.member.dto.MemberSignUpDto;
import com.uhpoo.ireon.api.service.member.dto.MemberUpdateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
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
                                .header("Authorization","Bearer ******")
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(document("member-logout",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization").description("Basic Auth Credentials")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER).description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING).description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.NULL).description("응답 데이터")
                        )


                ));
    }

    @DisplayName("내정보조회 API")
    @Test
    void getMember() throws Exception {

        given(memberService.getMember(any(String.class)))
                .willReturn(MemberResponse.builder()
                        .email("email@email.com")
                        .nickname("nickname")
                        .name("잔든뉴진")
                        .zipcode("11111")
                        .roadAddress("도화길 87")
                        .jibunAddress("마곡동 776")
                        .detailAddress("614호")
                        .phoneNumber("01012345678")
                        .build()
                );

        mockMvc.perform(
                        get("/member/info")
                                .param("email","email@email.com")
                                .header("Authorization","Bearer ******")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("member-getMember",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization").description("Basic Auth Credentials")
                        ),
                        queryParameters(parameterWithName("email").description("조회할 회원 이메일")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER).description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING).description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("응답 데이터"),
                                fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                                fieldWithPath("data.nickname").type(JsonFieldType.STRING).description("닉네임"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("data.zipcode").type(JsonFieldType.STRING).description("우편번호"),
                                fieldWithPath("data.roadAddress").type(JsonFieldType.STRING).description("도로명 주소"),
                                fieldWithPath("data.jibunAddress").type(JsonFieldType.STRING).description("지번 주소"),
                                fieldWithPath("data.detailAddress").type(JsonFieldType.STRING).description("상세 주소"),
                                fieldWithPath("data.phoneNumber").type(JsonFieldType.STRING).description("휴대폰 번호")
                        )

                ));
    }


    @DisplayName("내정보수정 API")
    @Test
    void updateMember() throws Exception {
        MemberUpdateRequest request = MemberUpdateRequest.builder()
                .email("email@email.com")
                .nickname("잔든뉴진")
                .name("잔뉴진")
                .zipcode("11111")
                .roadAddress("도화길87")
                .jibunAddress("마곡동776")
                .detailAddress("614호")
                .phoneNumber("01011112222")
                .build();

        given(memberService.updateMember(any(MemberUpdateDto.class)))
                .willReturn(MemberResponse.builder()
                        .email("email@email.com")
                        .nickname("잔든뉴진")
                        .name("잔뉴진")
                        .zipcode("22222")
                        .roadAddress("도화길 87")
                        .jibunAddress("마곡동 776")
                        .detailAddress("614호")
                        .phoneNumber("01012345678")
                        .build()
                );

        mockMvc.perform(
                        post("/member/info")
                                .header("Authorization","Bearer ******")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("member-updateMember",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization").description("Basic Auth Credentials")
                        ),
                        requestFields(
                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("zipcode").type(JsonFieldType.STRING).description("우편 번호"),
                                fieldWithPath("roadAddress").type(JsonFieldType.STRING).description("도로명 주소"),
                                fieldWithPath("jibunAddress").type(JsonFieldType.STRING).description("지번 주소"),
                                fieldWithPath("detailAddress").type(JsonFieldType.STRING).description("상세 주소"),
                                fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("휴대폰 번호")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER).description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING).description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("응답 데이터"),
                                fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                                fieldWithPath("data.nickname").type(JsonFieldType.STRING).description("닉네임"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("data.zipcode").type(JsonFieldType.STRING).description("우편 번호"),
                                fieldWithPath("data.roadAddress").type(JsonFieldType.STRING).description("도로명 주소"),
                                fieldWithPath("data.jibunAddress").type(JsonFieldType.STRING).description("지번 주소"),
                                fieldWithPath("data.detailAddress").type(JsonFieldType.STRING).description("상세 주소"),
                                fieldWithPath("data.phoneNumber").type(JsonFieldType.STRING).description("휴대폰 번호")
                        )

                ));
    }
}
