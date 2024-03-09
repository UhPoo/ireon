package com.uhpoo.ireon.api.controller.member.request;

import com.uhpoo.ireon.api.service.member.dto.MemberSignUpDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberSignUpRequest {
    private String email;
    private String password;
    private String nickname;


    @Builder
    public MemberSignUpRequest(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public MemberSignUpDto toDto() {
        return MemberSignUpDto.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
