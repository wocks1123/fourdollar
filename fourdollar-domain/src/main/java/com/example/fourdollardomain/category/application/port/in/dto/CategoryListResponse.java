package com.example.fourdollardomain.category.application.port.in.dto;

import java.util.List;

public record CategoryListResponse(
        Long id,
        String name,
        String description,
        int level,
        int displayOrder,
        List<CategoryListResponse> subCategories
) {
}
