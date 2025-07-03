package com.example.fourdollarapi.ui.product.dto;

import java.util.List;

public record RegisterProductOptionGroupRequest(
        String name,
        int displayOrder,
        List<RegisterProductOptionRequest> options
) {
}
