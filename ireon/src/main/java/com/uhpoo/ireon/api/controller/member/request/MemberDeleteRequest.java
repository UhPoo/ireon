package com.uhpoo.ireon.api.controller.member.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberDeleteRequest {
    private String email;
    private String password;

    @Builder
    public MemberDeleteRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
