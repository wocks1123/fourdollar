package com.example.fourdollardomain.coupon.application.port.out.dto;

import jakarta.validation.constraints.NotNull;

public record IssueCouponCommand(
        @NotNull Long couponId,
        @NotNull String memberId
) {
}
