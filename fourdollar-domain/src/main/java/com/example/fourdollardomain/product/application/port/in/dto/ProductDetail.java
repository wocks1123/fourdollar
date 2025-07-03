package com.example.fourdollardomain.product.application.port.in.dto;

import com.example.fourdollardomain.product.domain.ProductStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Builder
public record ProductDetail(
        Long id,
        String productCode,
        String name,
        String slug,
        String shortDescription,
        String fullDescription,
        BigDecimal basePrice,
        ZonedDateTime saleStartDate,
        ZonedDateTime saleEndDate,
        ProductStatus status,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt,
        List<OptionGroupDetail> optionGroups
) {

    public record OptionGroupDetail(
            Long id,
            String name,
            int displayOrder,
            List<OptionDetail> options
    ) {
    }

    public record OptionDetail(
            Long id,
            String name,
            String sku,
            int displayOrder,
            BigDecimal additionalPrice
    ) {
    }

}
