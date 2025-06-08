package com.example.fourdollardomain.category.application.port.in.dto;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public record RegisterCategoryCommand(
        @Nullable Long parentId,
        @NonNull String name,
        @NonNull String description,
        int displayOrder
) {
}
