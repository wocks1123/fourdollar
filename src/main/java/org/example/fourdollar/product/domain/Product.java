package org.example.fourdollar.product.domain;

import lombok.Getter;
import org.example.fourdollar.category.domain.Category;
import org.example.fourdollar.common.model.Money;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
public class Product {

    private Long id;

    private String name;

    private String slug;

    private String shortDescription;

    private String fullDescription;

    private Money price;

    private long sellerId;

    private Long brandId;

    private ProductStatus status;

    private List<ProductOptionGroup> optionGroup;

    private List<Category> categories;

    private List<Tag> tags;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;


    public Product(String name,
                   String slug,
                   String shortDescription,
                   String fullDescription,
                   Money price,
                   long sellerId,
                   Long brandId) {
        this.name = name;
        this.slug = slug;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.price = price;
        this.sellerId = sellerId;
        this.brandId = brandId;
        this.status = ProductStatus.Waiting;
    }

}
