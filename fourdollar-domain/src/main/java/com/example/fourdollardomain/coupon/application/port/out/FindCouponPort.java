package com.example.fourdollardomain.coupon.application.port.out;

import com.example.fourdollardomain.coupon.domain.Coupon;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface FindCouponPort {

    @NotNull Optional<Coupon> findById(@NotNull Long id);

}
