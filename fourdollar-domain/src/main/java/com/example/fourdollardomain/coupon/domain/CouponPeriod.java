package com.example.fourdollardomain.coupon.domain;

import java.time.ZonedDateTime;

public record CouponPeriod(
        ZonedDateTime startDate,
        ZonedDateTime endDate
) {
    public CouponPeriod {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("시작일과 종료일은 필수 입력입니다.");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("종료일은 시작일 이후로만 설정할 수 있습니다.");
        }
    }

    public boolean isExpired(ZonedDateTime baseDate) {
        return endDate.isBefore(baseDate) || endDate.isEqual(baseDate);
    }

}
