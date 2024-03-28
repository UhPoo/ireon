package com.uhpoo.ireon.api.controller.member.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemeberUpdatePasswordRequest {
    private String email;
    private String authCode;
    private String password;
}
