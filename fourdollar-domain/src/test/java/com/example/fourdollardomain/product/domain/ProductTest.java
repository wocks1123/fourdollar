package com.example.fourdollardomain.product.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {


    @Test
    @DisplayName("상품을 생성한다")
    void test01() {
        // given
        String productCode = "P12345";
        String name = "Test Product";
        String slug = "test-product";
        String shortDescription = "This is a test product.";
        String fullDescription = "This is a full description of the test product.";
        BigDecimal basePrice = BigDecimal.valueOf(15000);
        ZonedDateTime saleStartDate = ZonedDateTime.now().plusDays(3);
        ZonedDateTime saleEndDate = ZonedDateTime.now().plusDays(7);
        var optionGroups = List.of(ProductOptionGroup.builder()
                .name("Size")
                .displayOrder(1)
                .options(List.of(
                                ProductOption.builder()
                                        .name("Small")
                                        .sku("SKU-SMALL")
                                        .displayOrder(1)
                                        .additionalPrice(BigDecimal.ZERO)
                                        .build(),
                                ProductOption.builder()
                                        .name("Medium")
                                        .sku("SKU-MEDIUM")
                                        .displayOrder(2)
                                        .additionalPrice(BigDecimal.valueOf(2000))
                                        .build(),
                                ProductOption.builder()
                                        .name("Large")
                                        .sku("SKU-LARGE")
                                        .displayOrder(3)
                                        .additionalPrice(BigDecimal.valueOf(4000))
                                        .build()
                        )
                )
                .build()
        );
        var categories = List.of(
                ProductCategory.builder()
                        .categoryId(1L)
                        .build(),
                ProductCategory.builder()
                        .categoryId(2L)
                        .build()
        );

        // when
        Product product = Product.builder()
                .productCode(productCode)
                .name(name)
                .slug(slug)
                .shortDescription(shortDescription)
                .fullDescription(fullDescription)
                .basePrice(basePrice)
                .saleStartDate(saleStartDate)
                .saleEndDate(saleEndDate)
                .optionGroups(optionGroups)
                .categories(categories)
                .build();

        // then
        assertThat(product.getProductCode()).isEqualTo(productCode);
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getSlug()).isEqualTo(slug);
        assertThat(product.getShortDescription()).isEqualTo(shortDescription);
        assertThat(product.getFullDescription()).isEqualTo(fullDescription);
        assertThat(product.getBasePrice()).isEqualTo(basePrice);
        assertThat(product.getSaleStartDate()).isEqualTo(saleStartDate);
        assertThat(product.getSaleEndDate()).isEqualTo(saleEndDate);
        assertThat(product.getOptionGroups()).hasSize(1);
        assertThat(product.getOptionGroups().getFirst().getName()).isEqualTo("Size");
        assertThat(product.getStatus()).isEqualTo(ProductStatus.Waiting);
    }

}
