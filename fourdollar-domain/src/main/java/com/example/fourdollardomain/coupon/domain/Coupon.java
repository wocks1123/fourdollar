package com.example.fourdollardomain.coupon.domain;

import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class Coupon {

    private final Long id;
    private String name;
    private String description;
    private final CouponPeriod period;
    private CouponStatus status;
    private final CouponTarget target;
    private final IssuanceType issuanceType;
    private Integer issueCount;
    private Integer totalLimit;

    public Coupon(Long id, String name, String description, ZonedDateTime startDate, ZonedDateTime endDate,
                  CouponStatus status, CouponTarget target, IssuanceType issuanceType, Integer issueCount, Integer totalLimit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.period = new CouponPeriod(startDate, endDate);
        this.status = status;
        this.target = target;
        this.issuanceType = issuanceType;
        this.issueCount = issueCount;
        this.totalLimit = totalLimit;
    }

    public static Coupon createLimitedCoupon(String name,
                                             String description,
                                             ZonedDateTime startDate,
                                             ZonedDateTime endDate,
                                             CouponTarget target,
                                             Integer totalLimit) {
        return new Coupon(null, name, description, startDate, endDate, CouponStatus.Draft, target, IssuanceType.Limited, 0, totalLimit);
    }


    public void active() {
        if (this.status != CouponStatus.Draft) {
            throw new IllegalStateException("쿠폰은 Draft 상태에서만 활성화할 수 있습니다.");
        }
        this.status = CouponStatus.Active;
    }

    public void pause() {
        if (this.status != CouponStatus.Active) {
            throw new IllegalStateException("쿠폰은 Active 상태에서만 일시 중지할 수 있습니다.");
        }
        this.status = CouponStatus.Paused;
    }

    public boolean isIssuable() {
        if (this.status != CouponStatus.Active) {
            return false;
        }
        if (this.issuanceType == IssuanceType.Limited && this.issueCount >= this.totalLimit) {
            return false;
        }
        ZonedDateTime now = ZonedDateTime.now();
        return this.period.isExpired(now);
    }

    public void issue() {
        if (!this.isIssuable()) {
            throw new IllegalStateException("발급할 수 없는 쿠폰입니다.");
        }
        if (this.issuanceType == IssuanceType.Limited) {
            if (this.issueCount >= this.totalLimit) {
                throw new IllegalStateException("발급 한도를 초과했습니다.");
            }
            this.issueCount++;
        }
    }

}
