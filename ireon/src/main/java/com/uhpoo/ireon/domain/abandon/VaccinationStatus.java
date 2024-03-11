package com.uhpoo.ireon.domain.abandon;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 접종 여부
 *
 * @author 최영환
 */
@Getter
@RequiredArgsConstructor
public enum VaccinationStatus {

    ZERO("미완료"),
    FIRST("1차 완료"),
    SECOND("2차 완료"),
    THIRD("3차 완료");

    private final String text;
}
