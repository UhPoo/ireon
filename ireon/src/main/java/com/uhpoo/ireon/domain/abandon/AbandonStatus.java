package com.uhpoo.ireon.domain.abandon;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 유기동물 상태
 * 
 * @author 최영환
 */
@Getter
@RequiredArgsConstructor
public enum AbandonStatus {
    PROTECTING("임시보호 중"),
    SEARCHING("입양처 탐색중"),
    ADOPTED("입양됨");

    private final String text;
}
