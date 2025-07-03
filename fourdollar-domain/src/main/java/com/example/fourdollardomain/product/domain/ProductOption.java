package com.example.fourdollardomain.product.domain;

import com.example.fourdollardomain.common.exception.FdAssert;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "product_option")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_group_id")
    private ProductOptionGroup optionGroup;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "sku", nullable = false, length = 50)
    private String sku;

    @Column(name = "display_order", nullable = false)
    private int displayOrder;

    @Column(name = "additional_price", nullable = false)
    private BigDecimal additionalPrice;


    @Builder
    ProductOption(String name, String sku, int displayOrder, BigDecimal additionalPrice) {
        FdAssert.hasText(name, "Option name must not be blank");
        FdAssert.hasText(sku, "SKU must not be blank");
        FdAssert.notNull(additionalPrice, "Additional price must not be null");
        FdAssert.isTrue(additionalPrice.compareTo(BigDecimal.ZERO) >= 0, "Additional price must be non-negative");
        FdAssert.isTrue(displayOrder >= 0, "Display order must be non-negative");

        this.name = name;
        this.sku = sku;
        this.displayOrder = displayOrder;
        this.additionalPrice = additionalPrice;
    }

    void setOptionGroup(ProductOptionGroup optionGroup) {
        FdAssert.notNull(optionGroup, "Option group must not be null");
        this.optionGroup = optionGroup;
    }

}
