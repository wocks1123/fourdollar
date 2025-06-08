package com.example.fourdollardomain.coupon.application.port.in.dto;

import com.example.fourdollardomain.coupon.domain.CouponTarget;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

public record RegisterLimitedCouponCommand(
        @NotEmpty String name,
        @NotNull String description,
        @NotNull ZonedDateTime startDate,
        @NotNull ZonedDateTime endDate,
        CouponTarget couponTarget,
        int totalLimit
) {
}
