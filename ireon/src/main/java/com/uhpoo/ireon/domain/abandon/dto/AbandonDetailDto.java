package com.uhpoo.ireon.domain.abandon.dto;

import com.uhpoo.ireon.domain.abandon.AbandonStatus;
import com.uhpoo.ireon.domain.abandon.VaccinationStatus;
import com.uhpoo.ireon.domain.common.DeSexing;
import com.uhpoo.ireon.domain.common.animal.AnimalType;
import com.uhpoo.ireon.domain.common.animal.Gender;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class AbandonDetailDto {
    private Long abandonId;
    private String title;
    private String content;
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
    public AbandonDetailDto(Long abandonId, String title, String content, String author, AnimalType animalType,
                            String animalDetail, Gender animalGender, Integer animalAge,
                            VaccinationStatus vaccinationStatus, DeSexing deSexing, AbandonStatus abandonStatus,
                            String zipcode, String roadAddress, String jibunAddress, String detailAddress,
                            String phoneNumber, Boolean clipped, LocalDateTime createdDate) {
        this.abandonId = abandonId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.animalType = animalType.getText();
        this.animalDetail = animalDetail;
        this.animalGender = animalGender.getText();
        this.animalAge = animalAge;
        this.vaccinationStatus = vaccinationStatus.getText();
        this.deSexing = deSexing;
        this.abandonStatus = abandonStatus.getText();
        this.zipcode = zipcode;
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
        this.clipped = clipped;
        this.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
    }
}
