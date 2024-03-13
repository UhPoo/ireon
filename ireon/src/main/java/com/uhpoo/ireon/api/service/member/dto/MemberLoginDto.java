package com.uhpoo.ireon.api.service.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class MemberLoginDto {
    private String email;
    private String password;

    @Builder
    public MemberLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
