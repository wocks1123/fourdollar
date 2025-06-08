package com.example.fourdollardomain.product.application.port.in.dto;

import com.example.fourdollardomain.product.domain.ProductSummary;

import java.util.List;

public record ProductSearchResult(
        List<ProductSummary> products,
        int currentPage,
        int totalPages,
        long totalElements,
        boolean hasNext,
        boolean hasPrevious
) {

    public static ProductSearchResult of(List<ProductSummary> products,
                                         int currentPage,
                                         int totalPages,
                                         long totalElements) {
        return new ProductSearchResult(
                products,
                currentPage,
                totalPages,
                totalElements,
                currentPage < totalPages - 1,
                currentPage > 0
        );
    }

    public static ProductSearchResult empty() {
        return new ProductSearchResult(
                List.of(), 0, 0, 0, false, false
        );
    }

}
