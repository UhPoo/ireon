package com.uhpoo.ireon.api.controller.member;

import com.uhpoo.ireon.api.ApiResponse;
import com.uhpoo.ireon.api.controller.member.request.*;
import com.uhpoo.ireon.api.controller.member.response.EmailVerificationResponse;
import com.uhpoo.ireon.api.controller.member.response.MemberResponse;
import com.uhpoo.ireon.api.controller.member.response.MemberSignUpResponse;
import com.uhpoo.ireon.api.controller.member.response.TokenResponse;
import com.uhpoo.ireon.api.service.member.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MemberSignUpResponse> signup(@Valid @RequestBody MemberSignUpRequest request) {
        log.debug("MemberController#signup called.");
        log.debug("MemberSignUpRequest={}", request);

        MemberSignUpResponse response = memberService.signup(request.toDto());
        log.debug("MemberSignUpResponse={}",response);

        return ApiResponse.created(response);
    }

    @PostMapping("/login")
    public ApiResponse<TokenResponse> login(@RequestBody MemberLoginRequest request) {
        log.debug("MemberController#login called.");
        log.debug("MemberLoginRequest={}", request);

        TokenResponse response = memberService.login(request.toDto());
        log.debug("TokenDto={}",response);

        return ApiResponse.ok(response);
    }

    @PatchMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Object> logout(HttpServletRequest request) {
        log.debug("MemberController#logout called.");
        //String encryptedRefreshToken = jwtTokenProvider.resolveRefreshToken(request);
        String encryptedRefreshToken = "encryptedRefreshToken";
        //String accessToken = jwtTokenProvider.resolveAccessToken(request);
        String accessToken = "accessToken";

        memberService.logout(encryptedRefreshToken,accessToken); //TODO: 로그아웃 시 레디스 저장

        return ApiResponse.of(HttpStatus.NO_CONTENT,"Logged out successfully", null);
    }

    @GetMapping("/info")
    public ApiResponse<MemberResponse> getMemeber(@RequestParam("email") String email)  {
        log.debug("MemberController#getMemeber called.");
        // TODO: 로그인 사용자와 요청한 사용자 일치 여부 확인
        //String memberEmail = SecurityUtil.getCurrentMemberUsername();
        String memberEmail = "email";
        log.debug("memberEmail={}",memberEmail);

        MemberResponse response = memberService.getMember(memberEmail);
        log.debug("response={}",response);

        return ApiResponse.ok(response);
    }

    @PostMapping("/info")
    public ApiResponse<MemberResponse> updateMember(@Valid @RequestBody MemberUpdateRequest request) {
        log.debug("MemberController#updateMember called.");
        log.debug("MemberUpdateRequest={}",request);
        // TODO: 로그인 사용자와 요청 사용자 일치 여부 확인

        MemberResponse response = memberService.updateMember(request.toDto());
        log.debug("response={}",response);

        return ApiResponse.ok(response);
    }

    @PostMapping("/email/verification-request")
    public ApiResponse sendCodeToEmail(@RequestParam("email") @Email String email) {
        log.debug("MemberController#sendMessage called.");
        log.debug("email={}",email);
        //TODO: 회원이 존재하는지 확인 => 존재하지 않는 경우 없는 회원입니다.
        memberService.sendCodeToEmail(email);

        return ApiResponse.ok(null);
    }

    @GetMapping("/email/verifications")
    public ApiResponse<EmailVerificationResponse> verifiedCodeFromEmail(@RequestParam("email") @Email String email,
                                                                        @RequestParam("code") String authCode) {
        log.debug("MemberController#verifiedCodeFromEmail called.");
        log.debug("email={}, authCode={}",email, authCode);

        EmailVerificationResponse response = memberService.verifiedCodeFromEmail(email, authCode);

        log.debug("response={}", response);

        return ApiResponse.ok(response);

    }

    @PatchMapping("/password")
    public ApiResponse<Object> updatePassword(@RequestBody @Valid MemeberUpdatePasswordRequest request) {
        log.debug("MemberController#updatePassword called.");
        log.debug("request={}",request);
        // TODO: 이메일 + 인증코드 인증 여부 확인
        memberService.updatePassword(request.getEmail(), request.getPassword());

        return ApiResponse.ok(null);
    }

    @PatchMapping("/login/password")
    public ApiResponse<Object> updateLoginPassword(@RequestBody @Valid MemberLoginUpdatePasswordRequest request) {
        log.debug("MemberController#updateLoginPassword called.");
        log.debug("request={}",request);
        // TODO: 이메일 + 비밀번호 일치 여부 확인
        memberService.updatePassword(request.getEmail(), request.getNewPwd());

        return ApiResponse.ok(null);
    }
}
