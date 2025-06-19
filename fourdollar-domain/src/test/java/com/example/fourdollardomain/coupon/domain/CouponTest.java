package com.example.fourdollardomain.coupon.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CouponTest {


    @Test
    @DisplayName("쿠폰을 생성한다.")
    void test01() {
        // given
        String name = "Test Coupon";
        String description = "Test Description";
        ZonedDateTime startDate = ZonedDateTime.now().plusDays(1);
        ZonedDateTime endDate = startDate.plusDays(30);
        CouponTarget target = CouponTarget.AllProducts;
        IssuanceType issuanceType = IssuanceType.Limited;
        Integer totalLimit = 10;

        // when
        Coupon coupon = new Coupon(name, description, startDate, endDate, target, issuanceType, totalLimit);

        // then
        assertThat(coupon.getName()).isEqualTo(name);
        assertThat(coupon.getDescription()).isEqualTo(description);
        assertThat(coupon.getPeriod().getStartDate()).isEqualTo(startDate);
        assertThat(coupon.getPeriod().getEndDate()).isEqualTo(endDate);
        assertThat(coupon.getTarget()).isEqualTo(target);
        assertThat(coupon.getIssuanceType()).isEqualTo(issuanceType);
        assertThat(coupon.getTotalLimit()).isEqualTo(totalLimit);
        assertThat(coupon.getStatus()).isEqualTo(CouponStatus.Draft);
        assertThat(coupon.getIssueCount()).isZero();
    }

    @Nested
    @DisplayName("쿠폰 활성화 테스트")
    class CouponActivationTest {

        @Test
        @DisplayName("Draft 상태의 쿠폰을 활성화한다.")
        void test01() {
            // given
            ZonedDateTime startDate = ZonedDateTime.now().plusDays(1);
            ZonedDateTime endDate = startDate.plusDays(30);
            Coupon coupon = new Coupon("Test Coupon", "Test Description", startDate, endDate, CouponTarget.AllProducts, IssuanceType.Limited, 10);

            // when
            coupon.active();

            // then
            assertThat(coupon.getStatus()).isEqualTo(CouponStatus.Active);
        }

        @Test
        @DisplayName("Active 상태의 쿠폰을 활성화하려고 하면 예외가 발생한다.")
        void test02() {
            // given
            ZonedDateTime startDate = ZonedDateTime.now().plusDays(1);
            ZonedDateTime endDate = startDate.plusDays(30);
            Coupon coupon = new Coupon("Test Coupon", "Test Description", startDate, endDate, CouponTarget.AllProducts, IssuanceType.Limited, 10);
            coupon.active();

            // when & then
            assertThrows(IllegalStateException.class, () -> {
                coupon.active();
            });
        }
    }

    @Nested
    @DisplayName("쿠폰 정지 테스트")
    class CouponPauseTest {

        @Test
        @DisplayName("Active 상태의 쿠폰을 정지한다.")
        void test01() {
            // given
            ZonedDateTime startDate = ZonedDateTime.now().plusDays(1);
            ZonedDateTime endDate = startDate.plusDays(30);
            Coupon coupon = new Coupon("Test Coupon", "Test Description", startDate, endDate, CouponTarget.AllProducts, IssuanceType.Limited, 10);
            coupon.active();

            // when
            coupon.pause();

            // then
            assertThat(coupon.getStatus()).isEqualTo(CouponStatus.Paused);
        }

        @Test
        @DisplayName("Draft 상태의 쿠폰을 정지하려고 하면 예외가 발생한다.")
        void test02() {
            // given
            ZonedDateTime startDate = ZonedDateTime.now().plusDays(1);
            ZonedDateTime endDate = startDate.plusDays(30);
            Coupon coupon = new Coupon("Test Coupon", "Test Description", startDate, endDate, CouponTarget.AllProducts, IssuanceType.Limited, 10);

            // when & then
            assertThrows(IllegalStateException.class, () -> {
                coupon.pause();
            });
        }
    }


}
