package org.example.fourdollar.product.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class ProductOptionGroup {

    private final Long productId;
    private final String name;
    private final int displayOrder;
    private final List<ProductOption> options;

    public ProductOptionGroup(Long productId, String name, int displayOrder, List<ProductOption> options) {
        this.productId = productId;
        this.name = name;
        this.displayOrder = displayOrder;
        this.options = options;
    }

}
