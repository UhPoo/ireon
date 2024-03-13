package com.uhpoo.ireon.api.controller.member.request;

import com.uhpoo.ireon.api.service.member.dto.MemberUpdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberUpdateRequest {
    private String email;
    private String nickname;
    private String name;
    private String zipcode;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;
    private String phoneNumber;

    @Builder
    public MemberUpdateRequest(String email, String nickname, String name, String zipcode, String roadAddress, String jibunAddress, String detailAddress, String phoneNumber) {
        this.email = email;
        this.nickname = nickname;
        this.name = name;
        this.zipcode = zipcode;
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
    }

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
