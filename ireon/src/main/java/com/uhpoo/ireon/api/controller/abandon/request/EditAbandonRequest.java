package com.uhpoo.ireon.api.controller.abandon.request;

import com.uhpoo.ireon.api.service.abandon.dto.EditAbandonDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditAbandonRequest {

    private String title;
    private String content;
    private String animalType;
    private String animalDetail;
    private String animalGender;
    private Integer age;
    private String vaccinationStatus;
    private Boolean neutralized;
    private String abandonStatus;
    private String zipcode;
    private String road;
    private String jibun;
    private String detail;
    private String phoneNumber;

    @Builder
    public EditAbandonRequest(String title, String content, String animalType, String animalDetail, String animalGender,
                              Integer age, String vaccinationStatus, Boolean neutralized, String abandonStatus,
                              String zipcode, String road, String jibun, String detail, String phoneNumber) {
        this.title = title;
        this.content = content;
        this.animalType = animalType;
        this.animalDetail = animalDetail;
        this.animalGender = animalGender;
        this.age = age;
        this.vaccinationStatus = vaccinationStatus;
        this.neutralized = neutralized;
        this.abandonStatus = abandonStatus;
        this.zipcode = zipcode;
        this.road = road;
        this.jibun = jibun;
        this.detail = detail;
        this.phoneNumber = phoneNumber;
    }

    public EditAbandonDto toDto() {
        return EditAbandonDto.builder()
                .title(this.title)
                .content(this.content)
                .animalType(this.animalType)
                .animalDetail(this.animalDetail)
                .animalGender(this.animalGender)
                .age(this.age)
                .vaccinationStatus(this.vaccinationStatus)
                .neutralized(this.neutralized)
                .abandonStatus(this.abandonStatus)
                .zipcode(this.zipcode)
                .road(this.road)
                .jibun(this.jibun)
                .detail(this.detail)
                .phoneNumber(this.phoneNumber)
                .build();
    }
}
