package com.example.fourdollardomain.product.domain;

import com.example.fourdollardomain.common.entity.BaseEntity;
import com.example.fourdollardomain.common.exception.FdAssert;
import com.example.fourdollardomain.common.exception.FdIllegalArgumentException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq")
    private Long id;

    @Column(name = "product_code", nullable = false, length = 20, unique = true)
    private String productCode;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "slug", nullable = false, length = 200, unique = true)
    private String slug;

    @Column(name = "short_description", length = 500)
    private String shortDescription;

    @Column(name = "full_description", columnDefinition = "TEXT")
    private String fullDescription;

    @Column(name = "base_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal basePrice;

    @Column(name = "status", nullable = false, length = 16)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOptionGroup> optionGroups = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCategory> categories = new ArrayList<>();

    @Builder
    public Product(String productCode,
                   String name,
                   String slug,
                   String shortDescription,
                   String fullDescription,
                   BigDecimal basePrice) {
        FdAssert.hasText(productCode, "Product code must not be empty");
        FdAssert.hasText(name, "Product name must not be empty");
        FdAssert.hasText(slug, "Product slug must not be empty");
        FdAssert.notNull(basePrice, "Base price must not be null");
        FdAssert.isTrue(basePrice.compareTo(BigDecimal.ZERO) >= 0, "Base price must be non-negative");

        this.productCode = productCode;
        this.name = name;
        this.slug = slug;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.basePrice = basePrice;
        this.status = ProductStatus.Waiting;
    }

    public void addOptionGroups(List<ProductOptionGroup> optionGroups) {
        FdAssert.notEmpty(optionGroups, "Option groups must not be empty");
        for (ProductOptionGroup optionGroup : optionGroups) {
            optionGroup.setProduct(this);
            this.optionGroups.add(optionGroup);
        }
    }

    public void addCategories(List<ProductCategory> categories) {
        FdAssert.notEmpty(categories, "Categories must not be empty");
        for (ProductCategory category : categories) {
            category.setProduct(this);
            this.categories.add(category);
        }
    }

    public boolean isReadyForSale() {
        if (status != ProductStatus.Waiting) {
            return false;
        }
        return optionGroups.stream().allMatch(ProductOptionGroup::isReadyForSale);
    }

    public void onSale() {
        if (isReadyForSale()) {
            throw new FdIllegalArgumentException("Product must be ready for sale before going on sale");
        }
        this.status = ProductStatus.OnSale;
    }

    public void suspend() {
        if (status != ProductStatus.OnSale) {
            throw new FdIllegalArgumentException("Product must be on sale to be suspended");
        }
        this.status = ProductStatus.Suspended;
    }

    public void discontinue() {
        if (status != ProductStatus.OnSale && status != ProductStatus.Suspended) {
            throw new FdIllegalArgumentException("Product must be on sale to be suspended");
        }
        this.status = ProductStatus.Discontinued;
    }

    public List<ProductOption> getOptions() {
        return optionGroups.stream()
                .flatMap(optionGroup -> optionGroup.getOptions().stream())
                .toList();
    }

}
