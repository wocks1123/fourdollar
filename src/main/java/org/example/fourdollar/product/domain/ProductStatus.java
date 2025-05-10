package org.example.fourdollar.product.domain;

import lombok.Getter;

@Getter
public enum ProductStatus {
    Waiting("대기중"),
    OnSale("판매중"),
    OutOfStock("품절"),
    Deleted("삭제됨");

    private final String displayName;

    ProductStatus(String displayName) {
        this.displayName = displayName;
    }

}
