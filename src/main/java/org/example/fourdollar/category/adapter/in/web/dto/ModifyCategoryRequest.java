package org.example.fourdollar.category.adapter.in.web.dto;

public record ModifyCategoryRequest(
        String name,
        String description,
        int displayOrder
) {
}
