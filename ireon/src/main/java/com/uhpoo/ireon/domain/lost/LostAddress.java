package com.uhpoo.ireon.domain.lost;

import com.uhpoo.ireon.domain.common.Address;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 실종동물 게시판 위도/경도 엔티티
 *
 * @author CYJ
 */
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LostAddress extends Address {
    @Column(precision = 20, scale = 17)
    private BigDecimal latitude;

    @Column(precision = 20, scale = 17)
    private BigDecimal longitude;

    @Builder(builderMethodName = "LostAddressBuilder")
    public LostAddress(String zipcode, String road, String jibun, String detail
                       , BigDecimal latitude, BigDecimal longitude) {
        super(zipcode, road, jibun, detail);
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
