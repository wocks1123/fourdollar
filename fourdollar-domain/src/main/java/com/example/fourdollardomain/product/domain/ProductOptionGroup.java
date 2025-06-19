package com.example.fourdollardomain.product.domain;

import com.example.fourdollardomain.common.exception.FdAssert;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_option")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProductOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 500)
    private int displayOrder;

    @OneToMany(mappedBy = "optionGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ProductOption> options = new ArrayList<>();

    @Builder
    ProductOptionGroup(String name, int displayOrder, List<ProductOption> options) {
        FdAssert.hasText(name, "Option group name must not be blank");
        FdAssert.isTrue(displayOrder >= 0, "Display order must be non-negative");
        FdAssert.notEmpty(options, "Option group must have at least one option");

        this.name = name;
        this.displayOrder = displayOrder;
        this.addOptions(options);
    }

    void addOptions(List<ProductOption> options) {
        for (ProductOption option : options) {
            option.setOptionGroup(this);
            this.options.add(option);
        }
    }

    void setProduct(Product product) {
        FdAssert.notNull(product, "Product must not be null");
        this.product = product;
    }

}
