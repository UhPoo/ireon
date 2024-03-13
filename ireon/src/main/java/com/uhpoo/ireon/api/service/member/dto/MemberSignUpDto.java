package com.uhpoo.ireon.api.service.member.dto;

import lombok.Builder;


public class MemberSignUpDto {
    private String email;
    private String password;
    private String nickname;

    @Builder
    public MemberSignUpDto(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
