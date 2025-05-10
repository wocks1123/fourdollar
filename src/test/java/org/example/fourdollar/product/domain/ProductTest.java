package org.example.fourdollar.product.domain;

import org.example.fourdollar.common.model.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    @DisplayName("생성한 상품은 대기중(Waiting) 상태이다.")
    void test01() {
        // given
        Product product = new Product(
                "상품명",
                "slug",
                "",
                "",
                new Money("KRW", new BigDecimal(1000)),
                1L,
                1L
        );

        // when
        ProductStatus status = product.getStatus();

        // then
        assertThat(status).isEqualTo(ProductStatus.Waiting);
    }

}
