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
    private String animalDetail;
    private String animalGender;
    private Integer animalAge;
    private String vaccinationStatus;
    private Boolean neutralized;
    private String abandonStatus;
    private String zipcode;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;
    private String phoneNumber;
    private String createdDate;

    @Builder
    public AbandonResponse(String title, String author, String animalType, String animalDetail, String animalGender,
                           Integer animalAge, String vaccinationStatus, Boolean neutralized, String abandonStatus,
                           String zipcode, String roadAddress, String jibunAddress, String detailAddress,
                           String phoneNumber, String createdDate) {
        this.title = title;
        this.author = author;
        this.animalType = animalType;
        this.animalDetail = animalDetail;
        this.animalGender = animalGender;
        this.animalAge = animalAge;
        this.vaccinationStatus = vaccinationStatus;
        this.neutralized = neutralized;
        this.abandonStatus = abandonStatus;
        this.zipcode = zipcode;
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
    }
}
