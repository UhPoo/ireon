package com.uhpoo.ireon.domain.common.animal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 동물 성별 
 * 
 * @author 최영환
 */
@Getter
@RequiredArgsConstructor
public enum Gender {
    
    MALE("수컷"),
    FEMALE("암컷");

    private final String text;
}
