package com.uhpoo.ireon.api.service.abandon.dto;

import com.uhpoo.ireon.domain.abandon.Abandon;
import com.uhpoo.ireon.domain.abandon.AbandonStatus;
import com.uhpoo.ireon.domain.abandon.VaccinationStatus;
import com.uhpoo.ireon.domain.common.Address;
import com.uhpoo.ireon.domain.common.DeSexing;
import com.uhpoo.ireon.domain.common.animal.AnimalInfo;
import com.uhpoo.ireon.domain.common.animal.AnimalType;
import com.uhpoo.ireon.domain.common.animal.Gender;
import com.uhpoo.ireon.domain.member.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 유기동물 게시글 생성 DTO
 *
 * @author 최영환
 */
@Data
@NoArgsConstructor
public class CreateAbandonDto {

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
    public CreateAbandonDto(String title, String content, String animalType, String animalDetail, String animalGender,
                            Integer animalAge, String vaccinationStatus, String deSexing, String abandonStatus,
                            String zipcode, String roadAddress, String jibunAddress, String detailAddress,
                            String phoneNumber) {
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

    public Abandon toEntity(Member member) {
        return Abandon.builder()
                .title(this.title)
                .content(this.content)
                .animalInfo(createAnimalInfo())
                .vaccinationStatus(VaccinationStatus.valueOf(this.vaccinationStatus))
                .deSexing(DeSexing.valueOf(this.deSexing))
                .abandonStatus(AbandonStatus.valueOf(this.abandonStatus))
                .address(createAddress())
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

    private Address createAddress() {
        return Address.builder()
                .zipcode(this.zipcode)
                .road(this.roadAddress)
                .jibun(this.jibunAddress)
                .detail(this.detailAddress)
                .build();
    }
}
