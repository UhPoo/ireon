package com.uhpoo.ireon.api.service.abandon.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 유기동물 게시글 생성 DTO
 * 
 * @author 최영환
 */
@Data
public class CreateAbandonDto {

    private String title;
    private String content;
    private String animalType;
    private String animalDetail;
    private String animalGender;
    private String foundTime;
    private String foundLoc;
    private String phoneNumber;

    @Builder
    public CreateAbandonDto(String title, String content, String animalType, String animalDetail, String animalGender, String foundTime, String foundLoc, String phoneNumber) {
        this.title = title;
        this.content = content;
        this.animalType = animalType;
        this.animalDetail = animalDetail;
        this.animalGender = animalGender;
        this.foundTime = foundTime;
        this.foundLoc = foundLoc;
        this.phoneNumber = phoneNumber;
    }
}
