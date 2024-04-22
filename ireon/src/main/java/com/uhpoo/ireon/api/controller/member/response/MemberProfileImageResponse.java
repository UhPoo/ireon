package com.uhpoo.ireon.api.controller.member.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberProfileImageResponse {
    private String email;
    private String publicUrl;

    @Builder
    public MemberProfileImageResponse(String email, String publicUrl) {
        this.email = email;
        this.publicUrl = publicUrl;
    }
}
