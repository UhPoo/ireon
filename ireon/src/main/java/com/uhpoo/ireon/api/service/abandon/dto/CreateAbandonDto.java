package com.uhpoo.ireon.api.service.abandon.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 유기동물 게시글 생성 DTO
 * 
 * @author 최영환
 */
@Data
@NoArgsConstructor
public class CreateAbandonDto {

    private String title;
    private String content;
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

    @Builder
    public CreateAbandonDto(String title, String content, String animalType, String animalDetail, String animalGender,
                            Integer animalAge, String vaccinationStatus, Boolean neutralized, String abandonStatus,
                            String zipcode, String roadAddress, String jibunAddress, String detailAddress,
                            String phoneNumber) {
        this.title = title;
        this.content = content;
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
    }
}
