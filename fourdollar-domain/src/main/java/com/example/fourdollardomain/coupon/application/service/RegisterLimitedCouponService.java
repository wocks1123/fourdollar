package com.example.fourdollardomain.coupon.application.service;

import com.example.fourdollardomain.coupon.application.port.in.RegisterLimitedCouponUseCase;
import com.example.fourdollardomain.coupon.application.port.in.dto.RegisterLimitedCouponCommand;
import com.example.fourdollardomain.coupon.application.port.out.SaveCouponPort;
import com.example.fourdollardomain.coupon.domain.Coupon;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class RegisterLimitedCouponService implements RegisterLimitedCouponUseCase {

    private final SaveCouponPort saveCouponPort;


    @Override
    public @NotNull Long registerCoupon(@NotNull RegisterLimitedCouponCommand command) {
        Coupon coupon = Coupon.createLimitedCoupon(
                command.name(),
                command.description(),
                command.startDate(),
                command.endDate(),
                command.couponTarget(),
                command.totalLimit()
        );
        Coupon savedCoupon = saveCouponPort.save(coupon);
        return savedCoupon.getId();
    }

}
