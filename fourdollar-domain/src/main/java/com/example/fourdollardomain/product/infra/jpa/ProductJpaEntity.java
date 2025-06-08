package com.example.fourdollardomain.product.infra.jpa;

import com.example.fourdollardomain.product.domain.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProductJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private String price;

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

    public Product toDomain() {
        return new Product(id, title, image, price, mallName, brand, maker, category1, category2, category3, category4);
    }

    public static ProductJpaEntity from(Product product) {
        ProductJpaEntity entity = new ProductJpaEntity();
        entity.id = product.getId();
        entity.title = product.getTitle();
        entity.image = product.getImage();
        entity.price = product.getPrice();
        entity.mallName = product.getMallName();
        entity.brand = product.getBrand();
        entity.maker = product.getMaker();
        entity.category1 = product.getCategory1();
        entity.category2 = product.getCategory2();
        entity.category3 = product.getCategory3();
        entity.category4 = product.getCategory4();
        return entity;
    }

}
