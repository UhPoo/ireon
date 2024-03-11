package com.uhpoo.ireon.api.controller.abandon.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AbandonResponse {

    private String title;
    private String author;
    private String animalType;
    private String abandonStatus;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;
    private String phoneNumber;
    private Boolean clipped;
    private String createdDate;

    @Builder
    public AbandonResponse(String title, String author, String animalType, String abandonStatus, String roadAddress,
                           String jibunAddress, String detailAddress, String phoneNumber, Boolean clipped,
                           String createdDate) {
        this.title = title;
        this.author = author;
        this.animalType = animalType;
        this.abandonStatus = abandonStatus;
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
        this.clipped = clipped;
        this.createdDate = createdDate;
    }
}
