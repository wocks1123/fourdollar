package com.example.fourdollardomain.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private int price;

    @Column(name = "mall_name")
    private String mallName;

    @Column(name = "brand")
    private String brand;

    @Column(name = "maker")
    private String maker;

    @Column(name = "category1")
    private String category1;

    @Column(name = "category2")
    private String category2;

    @Column(name = "category3")
    private String category3;

    @Column(name = "category4")
    private String category4;

    @Builder
    public Product(Long id, String title, String image, int price, String mallName, String brand, String maker, String category1, String category2, String category3, String category4) {
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
