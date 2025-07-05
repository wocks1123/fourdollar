package com.example.fourdollardomain.product.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProductTest {

    @Test
    @DisplayName("기본 정보로 상품을 생성할 수 있다.")
    void test0001() {
        // given
        final String productCode = "P12345";
        final String name = "Test Product";
        final String slug = "test-product";
        final String shortDescription = "This is a test product.";
        final String fullDescription = "This is a full description of the test product.";
        final BigDecimal basePrice = BigDecimal.valueOf(15000);

        // when
        Product product = Product.builder()
                .productCode(productCode)
                .name(name)
                .slug(slug)
                .shortDescription(shortDescription)
                .fullDescription(fullDescription)
                .basePrice(basePrice)
                .build();

        // then
        assertAll(
                () -> assertThat(product.getProductCode()).isEqualTo(productCode),
                () -> assertThat(product.getName()).isEqualTo(name),
                () -> assertThat(product.getSlug()).isEqualTo(slug),
                () -> assertThat(product.getShortDescription()).isEqualTo(shortDescription),
                () -> assertThat(product.getFullDescription()).isEqualTo(fullDescription),
                () -> assertThat(product.getBasePrice()).isEqualTo(basePrice),
                () -> assertThat(product.getStatus()).isEqualTo(ProductStatus.Waiting)
        );
    }

    @Test
    @DisplayName("상품의 옵션 그룹과 옵션을 추가할 수 있다.")
    void test0002() {
        // given
        final String productCode = "P12345";
        final String name = "Test Product";
        final String slug = "test-product";
        final String shortDescription = "This is a test product.";
        final String fullDescription = "This is a full description of the test product.";
        final BigDecimal basePrice = BigDecimal.valueOf(15000);
        Product product = Product.builder()
                .productCode(productCode)
                .name(name)
                .slug(slug)
                .shortDescription(shortDescription)
                .fullDescription(fullDescription)
                .basePrice(basePrice)
                .build();
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

        // when
        product.addOptionGroups(optionGroups);

        // then
        assertAll(
                () -> assertThat(product.getOptionGroups()).hasSize(1),
                () -> assertThat(product.getOptionGroups()).extracting(ProductOptionGroup::getName)
                        .containsExactly("Size"),
                () -> assertThat(product.getOptionGroups().getFirst().getOptions()).hasSize(3),
                () -> assertThat(product.getOptionGroups().getFirst().getOptions())
                        .extracting(ProductOption::getName)
                        .containsExactly("Small", "Medium", "Large")
        );


    }

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
                .build();

        // then
        assertThat(product.getProductCode()).isEqualTo(productCode);
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getSlug()).isEqualTo(slug);
        assertThat(product.getShortDescription()).isEqualTo(shortDescription);
        assertThat(product.getFullDescription()).isEqualTo(fullDescription);
        assertThat(product.getBasePrice()).isEqualTo(basePrice);
        assertThat(product.getOptionGroups()).hasSize(1);
        assertThat(product.getOptionGroups().getFirst().getName()).isEqualTo("Size");
        assertThat(product.getStatus()).isEqualTo(ProductStatus.Waiting);
    }

}
