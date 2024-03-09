package com.uhpoo.ireon.api.controller.member.request;

import com.uhpoo.ireon.api.service.member.dto.MemberLoginDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberLoginRequest {
    private String email;
    private String password;

    public MemberLoginDto toDto() {
        return MemberLoginDto.builder()
                .email(email)
                .password(password)
                .build();
    }
}
