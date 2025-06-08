package com.example.fourdollardomain.category.application.port.in.dto;

public record ModifyCategoryCommand(
        Long categoryId,
        String name,
        String description,
        int displayOrder
) {
}
