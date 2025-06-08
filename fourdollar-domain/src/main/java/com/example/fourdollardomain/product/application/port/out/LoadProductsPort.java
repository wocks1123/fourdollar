package com.example.fourdollardomain.product.application.port.out;

import com.example.fourdollardomain.product.application.port.in.dto.ProductSearchResult;
import org.jetbrains.annotations.NotNull;

public interface LoadProductsPort {

    // TODO 검색 조건을 반영한 검색
    @NotNull ProductSearchResult findProducts(int page, int size);

}
