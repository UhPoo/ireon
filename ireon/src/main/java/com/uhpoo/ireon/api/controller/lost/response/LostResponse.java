package com.uhpoo.ireon.api.controller.lost.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 실종동물 게시글 목록 조회 응답 DTO
 *
 * @author CYJ
 */
@Data
@NoArgsConstructor
public class LostResponse {
    private Long LostId;
    private String title;
    private String author;
    private String animalType;
    private String lostStatus;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String phoneNumber;
    private Boolean clipped;
    private String createdDate;

    @Builder
    public LostResponse(Long LostId, String title, String author, String animalType, String lostStatus,
                        BigDecimal latitude, BigDecimal longitude, String phoneNumber, Boolean clipped, String createdDate) {
        this.LostId = LostId;
        this.title = title;
        this.author = author;
        this.animalType = animalType;
        this.lostStatus = lostStatus;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phoneNumber = phoneNumber;
        this.clipped = clipped;
        this.createdDate = createdDate;
    }
}
