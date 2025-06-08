package com.example.fourdollardomain.coupon.application.port.out;

import com.example.fourdollardomain.coupon.application.port.out.dto.IssueCouponCommand;
import jakarta.validation.constraints.NotNull;

public interface IssueCouponUseCase {

    void issueCoupon(@NotNull IssueCouponCommand command);

}
