package com.uhpoo.ireon.api.controller.member.request;

import com.uhpoo.ireon.api.service.member.dto.MemberLoginDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberLoginRequest {
    private String email;
    private String password;


    @Builder
    public MemberLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public MemberLoginDto toDto() {
        return MemberLoginDto.builder()
                .email(email)
                .password(password)
                .build();
    }
}
