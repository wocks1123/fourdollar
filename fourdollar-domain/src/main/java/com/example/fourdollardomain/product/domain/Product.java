package com.example.fourdollardomain.product.domain;

import lombok.Getter;

@Getter
public class Product {

    private final Long id;
    private final String title;
    private final String image;
    private final String price;
    private final String mallName;
    private final String brand;
    private final String maker;
    private final String category1;
    private final String category2;
    private final String category3;
    private final String category4;


    public Product(Long id, String title, String image, String price, String mallName, String brand, String maker, String category1, String category2, String category3, String category4) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.price = price;
        this.mallName = mallName;
        this.brand = brand;
        this.maker = maker;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.category4 = category4;
    }

}
