package com.uhpoo.ireon.domain.lost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 실종동물 상태
 *
 * @author CYJ
 */
@Getter
@RequiredArgsConstructor
public enum LostStatus {
    LOST("찾고있음"),
    DISCOVERED("발견됨"),
    PROTECTING("임시보호 중"),
    UNKNOWN("확인불가"),
    RETURNED("귀가");

    private final String text;
}
