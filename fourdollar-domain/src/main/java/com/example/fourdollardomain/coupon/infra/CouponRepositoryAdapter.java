package com.example.fourdollardomain.coupon.infra;

import com.example.fourdollardomain.coupon.application.port.out.FindCouponPort;
import com.example.fourdollardomain.coupon.application.port.out.SaveCouponPort;
import com.example.fourdollardomain.coupon.domain.Coupon;
import com.example.fourdollardomain.coupon.infra.jpa.CouponJpaEntity;
import com.example.fourdollardomain.coupon.infra.jpa.CouponJpaRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
class CouponRepositoryAdapter implements SaveCouponPort, FindCouponPort {

    private final CouponJpaRepository couponJpaRepository;


    @Override
    public @NotNull Coupon save(@NotNull Coupon coupon) {
        return couponJpaRepository.save(CouponJpaEntity.from(coupon))
                .toDomain();
    }

    @Override
    public @NotNull Optional<Coupon> findById(@NotNull Long id) {
        return couponJpaRepository.findById(id)
                .map(CouponJpaEntity::toDomain);
    }
}
