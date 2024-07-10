package com.uhpoo.ireon.domain.abandon.dto;

import com.uhpoo.ireon.domain.common.DeSexing;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AbandonDetailDto {
    private Long abandonId;
    private String title;
    private String author;
    private String animalType;
    private String animalDetail;
    private String animalGender;
    private Integer animalAge;
    private String vaccinationStatus;
    private DeSexing deSexing;
    private String abandonStatus;
    private String zipcode;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;
    private String phoneNumber;
    private Boolean clipped;
    private String createdDate;

    @Builder
    public AbandonDetailDto(Long abandonId, String title, String author, String animalType, String animalDetail,
                                 String animalGender, Integer animalAge, String vaccinationStatus, DeSexing deSexing,
                                 String abandonStatus, String zipcode, String roadAddress, String jibunAddress,
                                 String detailAddress, String phoneNumber, Boolean clipped, String createdDate) {
        this.abandonId = abandonId;
        this.title = title;
        this.author = author;
        this.animalType = animalType;
        this.animalDetail = animalDetail;
        this.animalGender = animalGender;
        this.animalAge = animalAge;
        this.vaccinationStatus = vaccinationStatus;
        this.deSexing = deSexing;
        this.abandonStatus = abandonStatus;
        this.zipcode = zipcode;
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
        this.clipped = clipped;
        this.createdDate = createdDate;
    }
}
