package com.uhpoo.ireon.api.controller.member.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberInfoResponse {
    private String email;
    private String nickname;
    private String name;
    private String zipcode;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;
    private String phoneNumber;

    @Builder
    public MemberInfoResponse(String email, String nickname, String name, String zipcode, String roadAddress, String jibunAddress, String detailAddress, String phoneNumber) {
        this.email = email;
        this.nickname = nickname;
        this.name = name;
        this.zipcode = zipcode;
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
    }
}
