package com.uhpoo.ireon.api.service.member;

import com.uhpoo.ireon.api.controller.member.response.MemberResponse;
import com.uhpoo.ireon.api.controller.member.response.MemberSignUpResponse;
import com.uhpoo.ireon.api.controller.member.response.TokenResponse;
import com.uhpoo.ireon.api.service.member.dto.MemberLoginDto;
import com.uhpoo.ireon.api.service.member.dto.MemberSignUpDto;
import com.uhpoo.ireon.api.service.member.dto.MemberUpdateDto;
import com.uhpoo.ireon.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
