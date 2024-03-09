package com.uhpoo.ireon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class Address {
    @Column(length = 5)
    private String zipcode;
    @Column(name = "road_address")
    private String road;
    @Column(name = "jibun_address")
    private String jibun;
    @Column(name = "detail_address")
    private String detail;
}
