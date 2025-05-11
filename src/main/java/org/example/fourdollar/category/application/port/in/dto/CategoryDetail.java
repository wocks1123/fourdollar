package org.example.fourdollar.category.application.port.in.dto;

import org.example.fourdollar.category.domain.Category;

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
