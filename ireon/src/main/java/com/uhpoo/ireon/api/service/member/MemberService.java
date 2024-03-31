package com.uhpoo.ireon.api.service.member;

import com.uhpoo.ireon.api.controller.member.request.MemberDeleteRequest;
import com.uhpoo.ireon.api.controller.member.response.*;
import com.uhpoo.ireon.api.service.member.dto.MemberLoginDto;
import com.uhpoo.ireon.api.service.member.dto.MemberSignUpDto;
import com.uhpoo.ireon.api.service.member.dto.MemberUpdateDto;
import com.uhpoo.ireon.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberSignUpResponse signup(MemberSignUpDto dto) {
        return null;
    }

    public TokenResponse login(MemberLoginDto dto) {
        return null;
    }

    public void logout(String encryptedRefreshToken, String accessToken) {
    }

    public MemberResponse getMember(String memberEmail) {
        return null;
    }

    public MemberResponse updateMember(MemberUpdateDto dto) {
        return null;
    }

    public void sendCodeToEmail(String email) {

    }

    public EmailVerificationResponse verifiedCodeFromEmail(String email, String authCode) {
        return null;
    }

    public void updatePassword(String email, String pwd) {

    }

    public void deleteMember(MemberDeleteRequest request) {
    }

    public MemberProfileImageResponse updateMemberProfileImage(String email, MultipartFile image) {
        return null;
    }
}
