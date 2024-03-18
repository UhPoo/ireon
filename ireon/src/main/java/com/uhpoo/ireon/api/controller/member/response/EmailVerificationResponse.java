package com.uhpoo.ireon.api.controller.member.response;

import lombok.Getter;

@Getter
public class EmailVerificationResponse {
    String email;
    String authCode;
    boolean result;

}
