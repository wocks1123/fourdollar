package com.example.fourdollardomain.product.domain;

import com.example.fourdollardomain.category.domain.Category;
import com.example.fourdollardomain.common.exception.FdAssert;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;


    @Builder
    ProductCategory(Long categoryId) {
        FdAssert.notNull(categoryId, "Category ID must not be null");

        this.categoryId = categoryId;
    }

    void setProduct(Product product) {
        FdAssert.notNull(product, "Product must not be null");
        this.product = product;
    }

}


