package com.uhpoo.ireon.api.controller.member.request;

import com.uhpoo.ireon.api.service.member.dto.MemberUpdateDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MemberUpdateRequest {
    private String email;
    private String nickname;
    private String name;
    private String zipcode;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;
    private String phoneNumber;

    public MemberUpdateDto toDto() {
        return MemberUpdateDto.builder()
                .email(email)
                .nickname(nickname)
                .name(name)
                .zipcode(zipcode)
                .roadAddress(roadAddress)
                .jibunAddress(jibunAddress)
                .detailAddress(detailAddress)
                .phoneNumber(phoneNumber)
                .build();
    }
}
