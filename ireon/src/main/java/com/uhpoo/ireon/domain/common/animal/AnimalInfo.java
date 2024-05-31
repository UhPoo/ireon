package com.uhpoo.ireon.domain.common.animal;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 동물 정보 값 타입
 *
 * @author 최영환
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnimalInfo {
    @Column(nullable = false, length = 30)
    private AnimalType animalType;

    @Column(nullable = false, length = 50)
    private String animalDetail;

    @Column(nullable = false, length = 10)
    private Gender animalGender;

    @Column(nullable = false)
    private Integer age;

    @Builder
    public AnimalInfo(AnimalType animalType, String animalDetail, Gender animalGender, Integer age) {
        this.animalType = animalType;
        this.animalDetail = animalDetail;
        this.animalGender = animalGender;
        this.age = age;
    }
}
