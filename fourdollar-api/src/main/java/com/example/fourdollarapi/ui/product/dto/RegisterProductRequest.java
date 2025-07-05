package com.example.fourdollarapi.ui.product.dto;

import java.math.BigDecimal;

public record RegisterProductRequest(
        String productCode,
        String name,
        String slug,
        String shortDescription,
        String fullDescription,
        BigDecimal basePrice
) {
}
