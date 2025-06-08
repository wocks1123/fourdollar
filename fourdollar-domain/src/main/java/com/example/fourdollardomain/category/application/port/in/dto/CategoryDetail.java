package com.example.fourdollardomain.category.application.port.in.dto;

import com.example.fourdollardomain.category.domain.Category;

import java.util.List;

public record CategoryDetail(
        Long id,
        Long parentId,
        String name,
        String description,
        int level,
        int displayOrder,
        List<CategoryDetail> subCategories
) {

    public static CategoryDetail from(Category category) {
        return new CategoryDetail(
                category.getId(),
                category.getParentId(),
                category.getName(),
                category.getDescription(),
                category.getLevel(),
                category.getDisplayOrder(),
                category.getSubCategories().stream()
                        .map(CategoryDetail::from)
                        .toList()
        );
    }

}
