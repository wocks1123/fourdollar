package com.example.fourdollardomain.coupon.infra.persistence;

import com.example.fourdollardomain.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponJpaRepository extends JpaRepository<Coupon, Long> {
}
