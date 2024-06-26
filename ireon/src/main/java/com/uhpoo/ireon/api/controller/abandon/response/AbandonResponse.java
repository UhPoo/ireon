package com.uhpoo.ireon.api.controller.abandon.response;

import com.uhpoo.ireon.domain.abandon.AbandonStatus;
import com.uhpoo.ireon.domain.common.animal.AnimalType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class AbandonResponse {

    private Long abandonId;
    private String title;
    private String author;
    private String animalType;
    private String abandonStatus;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;
    private String phoneNumber;
    private Boolean clipped;
    private String thumbnailUrl;
    private String createdDate;

    @Builder
    public AbandonResponse(Long abandonId, String title, String author, AnimalType animalType, AbandonStatus abandonStatus,
                           String roadAddress, String jibunAddress, String detailAddress, String phoneNumber,
                           Boolean clipped, String thumbnailUrl, LocalDateTime createdDate) {
        this.abandonId = abandonId;
        this.title = title;
        this.author = author;
        this.animalType = animalType.getText();
        this.abandonStatus = abandonStatus.getText();
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
        this.clipped = clipped;
        this.thumbnailUrl = thumbnailUrl;
        this.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
    }
}
