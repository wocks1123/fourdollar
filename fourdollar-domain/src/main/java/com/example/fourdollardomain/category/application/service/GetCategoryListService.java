package com.example.fourdollardomain.category.application.service;


import com.example.fourdollardomain.category.application.port.in.GetCategoryListUseCase;
import com.example.fourdollardomain.category.application.port.in.dto.CategoryListResponse;
import com.example.fourdollardomain.category.application.port.out.LoadCategoryListPort;
import com.example.fourdollardomain.category.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
                        .toList()
        );
    }
}
