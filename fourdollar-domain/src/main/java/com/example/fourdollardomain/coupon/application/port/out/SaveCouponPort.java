package com.example.fourdollardomain.coupon.application.port.out;

import com.example.fourdollardomain.coupon.domain.Coupon;
import org.jetbrains.annotations.NotNull;

public interface SaveCouponPort {

    @NotNull Coupon save(@NotNull Coupon coupon);

}
