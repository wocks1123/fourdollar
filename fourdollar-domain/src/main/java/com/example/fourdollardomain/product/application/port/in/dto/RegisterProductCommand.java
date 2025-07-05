package com.example.fourdollardomain.product.application.port.in.dto;

import java.math.BigDecimal;

public record RegisterProductCommand(
        String productCode,
        String name,
        String slug,
        String shortDescription,
        String fullDescription,
        BigDecimal basePrice
) {
}
