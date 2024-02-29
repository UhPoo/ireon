package com.uhpoo.ireon.api.controller.abandon.request;

import com.uhpoo.ireon.api.service.abandon.dto.CreateAbandonDto;
import lombok.Builder;
import lombok.Data;

/**
 * 유기동물 게시글 생성 요청 DTO
 *
 * @author 최영환
 */
@Data
public class CreateAbandonRequest {

    private String title;
    private String content;
    private String animalType;
    private String animalDetail;
    private String animalGender;
    private String foundTime;
    private String foundLoc;
    private String phoneNumber;

    @Builder
    public CreateAbandonRequest(String title, String content, String animalType, String animalDetail, String animalGender, String foundTime, String foundLoc, String phoneNumber) {
        this.title = title;
        this.content = content;
        this.animalType = animalType;
        this.animalDetail = animalDetail;
        this.animalGender = animalGender;
        this.foundTime = foundTime;
        this.foundLoc = foundLoc;
        this.phoneNumber = phoneNumber;
    }

    public CreateAbandonDto toDto() {
        return CreateAbandonDto.builder()
                .title(this.title)
                .content(this.content)
                .animalType(this.animalType)
                .animalDetail(this.animalDetail)
                .animalGender(this.animalGender)
                .foundTime(this.foundTime)
                .foundLoc(this.foundLoc)
                .phoneNumber(this.phoneNumber)
                .build();
    }
}
