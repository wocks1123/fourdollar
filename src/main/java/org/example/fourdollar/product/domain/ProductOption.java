package org.example.fourdollar.product.domain;

import lombok.Getter;
import org.example.fourdollar.common.model.Money;

@Getter
public class ProductOption {

    private final Long productOptionGroupId;
    private final String name;
    private final Money additionalPrice;
    private final int displayOrder;

    public ProductOption(Long productOptionGroupId, String name, Money additionalPrice, int displayOrder) {
        this.productOptionGroupId = productOptionGroupId;
        this.name = name;
        this.additionalPrice = additionalPrice;
        this.displayOrder = displayOrder;
    }

}
