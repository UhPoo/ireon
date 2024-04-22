package com.uhpoo.ireon.api.controller.member.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberLoginUpdatePasswordRequest {
    private String email;
    private String curPwd;
    private String newPwd;

    @Builder
    public MemberLoginUpdatePasswordRequest(String email, String curPwd, String newPwd) {
        this.email = email;
        this.curPwd = curPwd;
        this.newPwd = newPwd;
    }
}
