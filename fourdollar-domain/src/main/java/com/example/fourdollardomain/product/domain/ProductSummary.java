package com.example.fourdollardomain.product.domain;

public record ProductSummary(
        Long id,
        String title,
        String image,
        int price,
        String mallName,
        String brand,
        String category1,
        String category2,
        String category3,
        String category4
) {
}
