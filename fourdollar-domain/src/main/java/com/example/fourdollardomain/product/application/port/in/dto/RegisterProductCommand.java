package com.example.fourdollardomain.product.application.port.in.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record RegisterProductCommand(
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
