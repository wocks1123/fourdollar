package com.example.fourdollarbatch.jobconfig.product.upload;

public record ProductCsvDto(
        String title,
        String link,
        String image,
        Integer lprice,
        Integer hprice,
        String mallName,
        Long productId,
        String productType,
        String brand,
        String maker,
        String category1,
        String category2,
        String category3,
        String category4
) {
}
