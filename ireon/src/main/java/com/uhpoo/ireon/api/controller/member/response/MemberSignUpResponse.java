package com.uhpoo.ireon.api.controller.member.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberSignUpResponse {
    private Long memberId;
    private String email;
    private String nickname;

    @Builder
    public MemberSignUpResponse(Long memberId, String email, String nickname) {
        this.memberId = memberId;
        this.email = email;
        this.nickname = nickname;
    }
}
