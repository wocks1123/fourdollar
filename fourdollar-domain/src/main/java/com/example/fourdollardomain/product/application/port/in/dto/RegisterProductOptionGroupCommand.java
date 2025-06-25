package com.example.fourdollardomain.product.application.port.in.dto;

import java.math.BigDecimal;
import java.util.List;

public record RegisterProductOptionGroupCommand(
        Long productId,
        List<OptionGroupDto> optionGroups
) {

    public record OptionGroupDto(
            String name,
            int displayOrder,
            List<OptionDto> options
    ) {
    }

    public record OptionDto(
            String name,
            String sku,
            int displayOrder,
            BigDecimal additionalPrice
    ) {
    }

}
