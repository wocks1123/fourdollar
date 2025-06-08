package com.example.fourdollarapi.ui.category;

import com.example.fourdollarapi.ui.category.dto.ModifyCategoryRequest;
import com.example.fourdollarapi.ui.category.dto.RegisterCategoryRequest;
import com.example.fourdollarapi.ui.category.dto.RegisterCategoryResponse;
import com.example.fourdollardomain.category.application.port.in.GetCategoryListUseCase;
import com.example.fourdollardomain.category.application.port.in.GetCategoryUseCase;
import com.example.fourdollardomain.category.application.port.in.ModifyCategoryUseCase;
import com.example.fourdollardomain.category.application.port.in.RegisterCategoryUseCase;
import com.example.fourdollardomain.category.application.port.in.dto.CategoryDetail;
import com.example.fourdollardomain.category.application.port.in.dto.CategoryListResponse;
import com.example.fourdollardomain.category.application.port.in.dto.ModifyCategoryCommand;
import com.example.fourdollardomain.category.application.port.in.dto.RegisterCategoryCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
class CategoryRestController {

    private final RegisterCategoryUseCase registerCategoryUseCase;
    private final ModifyCategoryUseCase modifyCategoryUseCase;
    private final GetCategoryUseCase getCategoryUseCase;
    private final GetCategoryListUseCase getCategoryListUseCase;

    @PostMapping
    RegisterCategoryResponse registerCategory(@RequestBody RegisterCategoryRequest request) {
        return new RegisterCategoryResponse(registerCategoryUseCase.registerCategory(
                new RegisterCategoryCommand(
                        request.parentId(),
                        request.name(),
                        request.description(),
                        request.displayOrder()
                )
        ));
    }

    @PutMapping("/{categoryId}")
    void modifyCategory(@PathVariable Long categoryId,
                        @RequestBody ModifyCategoryRequest request) {
        modifyCategoryUseCase.modifyCategory(
                new ModifyCategoryCommand(
                        categoryId,
                        request.name(),
                        request.description(),
                        request.displayOrder()
                )
        );
    }

    @GetMapping("/{categoryId}")
    CategoryDetail getCategory(@PathVariable Long categoryId) {
        return getCategoryUseCase.getCategory(categoryId);
    }

    @GetMapping
    List<CategoryListResponse> getCategoryList() {
        return getCategoryListUseCase.getCategoryList();
    }

}
