package com.uhpoo.ireon.api.controller.abandon.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AbandonReportResponse {

    private Long abandonReportId;
    private String reason;
    private String reporter;
    private Long abandonId;
    private String title;
    private String author;
    private String animalType;
    private String abandonStatus;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;
    private String phoneNumber;
    private String createdDate;

    @Builder
    public AbandonReportResponse(Long abandonReportId, String reason, String reporter, Long abandonId, String title,
                                 String author, String animalType, String abandonStatus, String roadAddress,
                                 String jibunAddress, String detailAddress, String phoneNumber, String createdDate) {
        this.abandonReportId = abandonReportId;
        this.reason = reason;
        this.reporter = reporter;
        this.abandonId = abandonId;
        this.title = title;
        this.author = author;
        this.animalType = animalType;
        this.abandonStatus = abandonStatus;
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
    }
}
