package com.example.fourdollardomain.coupon.application.service;

import com.example.fourdollardomain.coupon.application.port.out.FindCouponPort;
import com.example.fourdollardomain.coupon.application.port.out.IssueCouponUseCase;
import com.example.fourdollardomain.coupon.application.port.out.dto.IssueCouponCommand;
import com.example.fourdollardomain.coupon.domain.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class IssueCouponService implements IssueCouponUseCase {

    private final FindCouponPort findCouponPort;


    @Override
    public void issueCoupon(IssueCouponCommand command) {

        Coupon coupon = findCouponPort.findById(command.couponId())
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 쿠폰입니다.(id : " + command.couponId()));
        

    }
}
