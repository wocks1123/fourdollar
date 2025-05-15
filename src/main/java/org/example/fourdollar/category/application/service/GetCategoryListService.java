package org.example.fourdollar.category.application.service;


import lombok.RequiredArgsConstructor;
import org.example.fourdollar.category.application.port.in.GetCategoryListUseCase;
import org.example.fourdollar.category.application.port.in.dto.CategoryListResponse;
import org.example.fourdollar.category.application.port.out.LoadCategoryListPort;
import org.example.fourdollar.category.domain.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetCategoryListService implements GetCategoryListUseCase {

    private final LoadCategoryListPort loadCategoryListPort;

    @Override
    public List<CategoryListResponse> getCategoryList() {
        List<Category> topLevelCategories = loadCategoryListPort.findAllRootCategories();

        return topLevelCategories.stream()
                .map(this::mapToCategoryListResponse)
                .toList();
    }

    private CategoryListResponse mapToCategoryListResponse(Category category) {
        return new CategoryListResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getLevel(),
                category.getDisplayOrder(),
                category.getSubCategories().stream()
                        .map(this::mapToCategoryListResponse)
                        .collect(Collectors.toList())
        );
    }
}
