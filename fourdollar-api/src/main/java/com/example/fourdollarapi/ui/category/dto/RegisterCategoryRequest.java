package com.example.fourdollarapi.ui.category.dto;

public record RegisterCategoryRequest(
        Long parentId,
        String name,
        String description,
        int displayOrder
) {
}
