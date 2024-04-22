package com.uhpoo.ireon.api.controller.member.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberUpdatePasswordRequest {
    private String email;
    private String authCode;
    private String password;

    @Builder
    public MemberUpdatePasswordRequest(String email, String authCode, String password) {
        this.email = email;
        this.authCode = authCode;
        this.password = password;
    }
}
