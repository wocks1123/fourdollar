package com.example.fourdollardomain.coupon.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "coupon")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    @AttributeOverride(name = "startDate", column = @Column(name = "special_start_date"))
    @AttributeOverride(name = "endDate", column = @Column(name = "special_end_date"))
    private CouponPeriod period;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CouponStatus status;

    @Column(name = "target", nullable = false)
    @Enumerated(EnumType.STRING)
    private CouponTarget target;

    @Column(name = "issuance_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private IssuanceType issuanceType;

    @Column(name = "issue_count")
    private Integer issueCount;

    @Column(name = "total_limit")
    private Integer totalLimit;


    Coupon(String name,
           String description,
           ZonedDateTime startDate,
           ZonedDateTime endDate,
           CouponTarget target,
           IssuanceType issuanceType,
           Integer totalLimit) {
        this.name = name;
        this.description = description;
        this.period = new CouponPeriod(startDate, endDate);
        this.target = target;
        this.issuanceType = issuanceType;
        this.totalLimit = totalLimit;
        this.status = CouponStatus.Draft; // 초기 상태는 Draft
        this.issueCount = 0; // 발급 횟수 초기화
    }

    public static Coupon createLimitedCoupon(String name,
                                             String description,
                                             ZonedDateTime startDate,
                                             ZonedDateTime endDate,
                                             CouponTarget target,
                                             Integer totalLimit) {
        return new Coupon(name, description, startDate, endDate, target, IssuanceType.Limited, totalLimit);
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
