package org.example.fourdollar.product.application.port.in.dto;

import org.example.fourdollar.common.model.Money;

public record RegisterProductCommand(
        String name,
        String slug,
        String shortDescription,
        String fullDescription,
        Money price,
        long sellerId,
        Long brandId
) {
}
