package com.example.fourdollardomain.category.application.service;

import com.example.fourdollardomain.category.application.port.in.GetCategoryUseCase;
import com.example.fourdollardomain.category.application.port.in.dto.CategoryDetail;
import com.example.fourdollardomain.category.application.port.out.LoadCategoryPort;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetCategoryService implements GetCategoryUseCase {

    private final LoadCategoryPort loadCategoryPort;

    @Override
    public @NotNull CategoryDetail getCategory(@NotNull Long categoryId) {
        return loadCategoryPort.findBy(categoryId)
                .map(CategoryDetail::from)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다. : " + categoryId));
    }

}
