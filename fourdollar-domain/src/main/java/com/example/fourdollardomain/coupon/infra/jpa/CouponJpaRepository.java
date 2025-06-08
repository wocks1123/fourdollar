package com.example.fourdollardomain.coupon.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponJpaRepository extends JpaRepository<CouponJpaEntity, Long> {
}
