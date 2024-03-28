package com.uhpoo.ireon.api.controller.member.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberLoginUpdatePasswordRequest {
    private String email;
    private String curPwd;
    private String newPwd;

}
