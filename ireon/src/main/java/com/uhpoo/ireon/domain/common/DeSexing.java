package com.uhpoo.ireon.domain.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 중성화 여부
 *
 * @author 최영환
 */
@Getter
@RequiredArgsConstructor
public enum DeSexing {
    DONE("완료"),
    UNDONE("미완료"),
    UNKNOWN("알수없음");

    private final String text;
}
