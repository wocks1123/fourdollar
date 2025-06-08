package com.example.fourdollardomain.product.application.port.in.dto;


import jakarta.annotation.Nullable;

public record ProductSearchCommand(
        @Nullable String keyword,
        @Nullable String mallName,
        @Nullable String brand,
        @Nullable String category1,
        @Nullable String category2,
        @Nullable String category3,
        @Nullable String category4,
        @Nullable String minPrice,
        @Nullable String maxPrice,
        int page,
        int size,
        @Nullable String sortBy,
        @Nullable String sortDirection
) {

    public ProductSearchCommand {
        if (page < 0) {
            throw new IllegalArgumentException("페이지는 0 이상이어야 합니다.");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("사이즈는 1 이상이어야 합니다.");
        }
    }

    public static ProductSearchCommand defaultSearch() {
        return new ProductSearchCommand(
                null, null, null, null, null, null, null,
                null, null, 0, 20, null, null
        );
    }

    public static ProductSearchCommand of(String keyword, int page, int size) {
        return new ProductSearchCommand(
                keyword, null, null, null, null, null, null,
                null, null, page, size, null, null
        );
    }

}
