package com.example.fourdollarapi.ui.category.dto;

public record ModifyCategoryRequest(
        String name,
        String description,
        int displayOrder
) {
}
