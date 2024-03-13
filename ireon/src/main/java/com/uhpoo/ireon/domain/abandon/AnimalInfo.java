package com.uhpoo.ireon.domain.abandon;

import jakarta.persistence.Column;
import lombok.AccessLevel;
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
}
