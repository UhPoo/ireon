package com.uhpoo.ireon.domain.abandon;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 동물 종류
 *
 * @author 최영환
 */
@Getter
@RequiredArgsConstructor
public enum AnimalType {

    DOG("개"),
    CAT("고양이"),
    HAMSTER("햄스터"),
    TURTLE("거북이");

    private final String text;
}
