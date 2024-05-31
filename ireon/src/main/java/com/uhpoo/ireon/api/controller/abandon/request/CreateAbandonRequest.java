package com.uhpoo.ireon.api.controller.abandon.request;

import com.uhpoo.ireon.api.service.abandon.dto.CreateAbandonDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 유기동물 게시글 생성 요청 DTO
 *
 * @author 최영환
 */
@Data
@NoArgsConstructor
public class CreateAbandonRequest {

    private String title;
    private String content;
    private String animalType;
    private String animalDetail;
    private String animalGender;
    private Integer animalAge;
    private String vaccinationStatus;
    private String deSexing;
    private String abandonStatus;
    private String zipcode;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;
    private String phoneNumber;

    @Builder
    public CreateAbandonRequest(String title, String content, String animalType, String animalDetail,
                                String animalGender, Integer animalAge, String vaccinationStatus, String deSexing,
                                String abandonStatus, String zipcode, String roadAddress, String jibunAddress,
                                String detailAddress, String phoneNumber) {
        this.title = title;
        this.content = content;
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
    }

    public CreateAbandonDto toDto() {
        return CreateAbandonDto.builder()
                .title(this.title)
                .content(this.content)
                .animalType(this.animalType)
                .animalDetail(this.animalDetail)
                .animalGender(this.animalGender)
                .animalAge(this.animalAge)
                .vaccinationStatus(this.vaccinationStatus)
                .deSexing(this.deSexing)
                .abandonStatus(this.abandonStatus)
                .zipcode(this.zipcode)
                .roadAddress(this.roadAddress)
                .jibunAddress(this.jibunAddress)
                .detailAddress(this.detailAddress)
                .phoneNumber(this.phoneNumber)
                .build();
    }
}
