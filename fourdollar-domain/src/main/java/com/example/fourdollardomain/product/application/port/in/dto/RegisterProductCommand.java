package com.example.fourdollardomain.product.application.port.in.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public record RegisterProductCommand(
        String productCode,
        String name,
        String slug,
        String shortDescription,
        String fullDescription,
        BigDecimal basePrice,
        ZonedDateTime saleStartDate,
        ZonedDateTime saleEndDate,
        List<Long> categoryIds,
        List<RegisterProductOptionGroupCommand> optionGroups
) {
}
