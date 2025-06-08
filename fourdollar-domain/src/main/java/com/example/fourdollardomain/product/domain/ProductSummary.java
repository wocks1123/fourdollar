package com.example.fourdollardomain.product.domain;

public record ProductSummary(
        Long id,
        String title,
        String image,
        String price,
        String mallName,
        String brand,
        String category1,
        String category2,
        String category3,
        String category4
) {

    public static ProductSummary from(Product product) {
        return new ProductSummary(
                product.getId(),
                product.getTitle(),
                product.getImage(),
                product.getPrice(),
                product.getMallName(),
                product.getBrand(),
                product.getCategory1(),
                product.getCategory2(),
                product.getCategory3(),
                product.getCategory4()
        );
    }

}
