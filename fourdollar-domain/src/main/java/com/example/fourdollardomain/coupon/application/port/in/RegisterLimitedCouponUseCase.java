package com.example.fourdollardomain.coupon.application.port.in;

import com.example.fourdollardomain.coupon.application.port.in.dto.RegisterLimitedCouponCommand;
import org.jetbrains.annotations.NotNull;

public interface RegisterLimitedCouponUseCase {

    @NotNull Long registerCoupon(@NotNull RegisterLimitedCouponCommand command);

}
