package com.uhpoo.ireon.api.service.lost.dto;

import com.uhpoo.ireon.domain.common.DeSexing;
import com.uhpoo.ireon.domain.common.animal.AnimalInfo;
import com.uhpoo.ireon.domain.common.animal.AnimalType;
import com.uhpoo.ireon.domain.common.animal.Gender;
import com.uhpoo.ireon.domain.lost.Lost;
import com.uhpoo.ireon.domain.lost.LostAddress;
import com.uhpoo.ireon.domain.lost.LostStatus;
import com.uhpoo.ireon.domain.member.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 실종동물 게시글 생성 DTO
 *
 * @author CYJ
 */
@Data
@NoArgsConstructor
public class CreateLostDto {
    private String title;
    private String content;
    private String animalType;
    private String animalDetail;
    private String animalGender;
    private Integer animalAge;
    private String deSexing;
    private String lostStatus;
    private String zipcode;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String phoneNumber;
    @Builder
    public CreateLostDto(String title, String content, String animalType, String animalDetail,
                         String animalGender, Integer animalAge, String deSexing, String lostStatus,
                         String zipcode, String roadAddress, String jibunAddress, String detailAddress,
                         BigDecimal latitude, BigDecimal longitude, String phoneNumber) {
        this.title = title;
        this.content = content;
        this.animalType = animalType;
        this.animalDetail = animalDetail;
        this.animalGender = animalGender;
        this.animalAge = animalAge;
        this.deSexing = deSexing;
        this.lostStatus = lostStatus;
        this.zipcode = zipcode;
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.detailAddress = detailAddress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phoneNumber = phoneNumber;
    }

    public Lost toEntity(Member member) {
        return Lost.builder()
                .title(this.title)
                .content(this.content)
                .animalInfo(createAnimalInfo())
                .deSexing(DeSexing.valueOf(this.deSexing))
                .lostStatus(LostStatus.valueOf(this.lostStatus))
                .lostAddress(createLostAddress())
                .phoneNumber(this.phoneNumber)
                .member(member)
                .build();
    }

    private AnimalInfo createAnimalInfo() {
        return AnimalInfo.builder()
                .animalType(AnimalType.valueOf(this.animalType))
                .animalDetail(this.animalDetail)
                .animalGender(Gender.valueOf(this.animalGender))
                .age(this.animalAge)
                .build();
    }

    private LostAddress createLostAddress() {
        return LostAddress.LostAddressBuilder()
                .zipcode(this.zipcode)
                .road(this.roadAddress)
                .jibun(this.jibunAddress)
                .detail(this.detailAddress)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .build();
    }
}
