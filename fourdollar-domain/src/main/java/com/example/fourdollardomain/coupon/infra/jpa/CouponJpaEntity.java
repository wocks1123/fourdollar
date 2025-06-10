package com.example.fourdollardomain.coupon.infra.jpa;

import com.example.fourdollardomain.coupon.domain.Coupon;
import com.example.fourdollardomain.coupon.domain.CouponStatus;
import com.example.fourdollardomain.coupon.domain.CouponTarget;
import com.example.fourdollardomain.coupon.domain.IssuanceType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "coupon")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CouponJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private ZonedDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private ZonedDateTime endDate;

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


    public static CouponJpaEntity from(Coupon coupon) {
        CouponJpaEntity entity = new CouponJpaEntity();
        entity.id = coupon.getId();
        entity.name = coupon.getName();
        entity.description = coupon.getDescription();
        entity.startDate = coupon.getPeriod().startDate();
        entity.endDate = coupon.getPeriod().endDate();
        entity.status = coupon.getStatus();
        entity.target = coupon.getTarget();
        entity.issuanceType = coupon.getIssuanceType();
        entity.issueCount = coupon.getIssueCount();
        entity.totalLimit = coupon.getTotalLimit();
        return entity;
    }

    public Coupon toDomain() {
        return new Coupon(
                id,
                name,
                description,
                startDate,
                endDate,
                status,
                target,
                issuanceType,
                issueCount,
                totalLimit
        );
    }

}
