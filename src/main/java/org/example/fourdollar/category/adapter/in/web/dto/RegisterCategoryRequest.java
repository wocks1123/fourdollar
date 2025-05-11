package org.example.fourdollar.category.adapter.in.web.dto;

public record RegisterCategoryRequest(
        Long parentId,
        String name,
        String description,
        int displayOrder
) {
}
