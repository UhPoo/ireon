package com.uhpoo.ireon.api.controller.member.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EmailVerificationResponse {
    String email;
    String authCode;
    boolean result;

    @Builder
    public EmailVerificationResponse(String email, String authCode, boolean result) {
        this.email = email;
        this.authCode = authCode;
        this.result = result;
    }
}
