package org.example.fourdollar.category.application.port.in.dto;

public record ModifyCategoryCommand(
        String name,
        String description,
        int displayOrder
) {
}
