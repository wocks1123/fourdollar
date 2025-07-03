package com.example.fourdollardomain.product.application.port.in.dto;

import java.math.BigDecimal;

public record RegisterProductOptionCommand(
        String name,
        String sku,
        int displayOrder,
        BigDecimal additionalPrice
) {
}
