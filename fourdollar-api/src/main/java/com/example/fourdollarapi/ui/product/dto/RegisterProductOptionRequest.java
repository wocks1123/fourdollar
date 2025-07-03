package com.example.fourdollarapi.ui.product.dto;

import java.math.BigDecimal;

public record RegisterProductOptionRequest(
        String name,
        String sku,
        int displayOrder,
        BigDecimal additionalPrice
) {
}
