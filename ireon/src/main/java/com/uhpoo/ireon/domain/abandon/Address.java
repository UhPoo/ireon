package com.uhpoo.ireon.domain.abandon;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 주소 값 타입
 *
 * @author 최영환
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @Column(length = 5)
    private String zipcode;

    private String roadAddress;

    private String jibunAddress;

    private String detailAddress;
}
