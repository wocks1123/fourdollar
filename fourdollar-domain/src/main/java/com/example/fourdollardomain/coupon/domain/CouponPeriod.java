package com.example.fourdollardomain.coupon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponPeriod {

    @Column(name = "start_date", nullable = false)
    private ZonedDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private ZonedDateTime endDate;

    public CouponPeriod(ZonedDateTime startDate, ZonedDateTime endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("시작일과 종료일은 필수 입력입니다.");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("종료일은 시작일 이후로만 설정할 수 있습니다.");
        }

        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isExpired(ZonedDateTime baseDate) {
        return endDate.isBefore(baseDate) || endDate.isEqual(baseDate);
    }

}
