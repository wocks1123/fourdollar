package com.example.fourdollardomain.product.application.port.out;

import com.example.fourdollardomain.product.application.port.in.dto.ProductSearchResult;
import com.example.fourdollardomain.product.domain.Product;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface FindProductsPort {

    // TODO 검색 조건을 반영한 검색
    @NotNull ProductSearchResult findProducts(int page, int size);

    Optional<Product> findById(@NotNull Long productId);

}
