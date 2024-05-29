package com.uhpoo.ireon.api.service.lost.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 실종동물 게시글 수정 DTO
 *
 * @author CYJ
 */
@Data
@NoArgsConstructor
public class EditLostDto {
    private String title;
    private String content;
    private String animalType;
    private String animalDetail;
    private String animalGender;
    private Integer animalAge;
    private Boolean neutralized;
    private String lostStatus;
    private String zipcode;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String phoneNumber;

    @Builder
    public EditLostDto(String title, String content, String animalType, String animalDetail, String animalGender, Integer animalAge, Boolean neutralized, String lostStatus, String zipcode, String roadAddress, String jibunAddress, String detailAddress, BigDecimal latitude, BigDecimal longitude, String phoneNumber) {
        this.title = title;
        this.content = content;
        this.animalType = animalType;
        this.animalDetail = animalDetail;
        this.animalGender = animalGender;
        this.animalAge = animalAge;
        this.neutralized = neutralized;
        this.lostStatus = lostStatus;
        this.zipcode = zipcode;
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.detailAddress = detailAddress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phoneNumber = phoneNumber;
    }
}
