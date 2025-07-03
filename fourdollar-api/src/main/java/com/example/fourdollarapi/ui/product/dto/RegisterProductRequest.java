package com.example.fourdollarapi.ui.product.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record RegisterProductRequest(
        String productCode,
        String name,
        String slug,
        String shortDescription,
        String fullDescription,
        BigDecimal basePrice,
        ZonedDateTime saleStartDate,
        ZonedDateTime saleEndDate
) {
}
