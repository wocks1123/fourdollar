package org.example.fourdollar.category.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.example.fourdollar.category.adapter.in.web.dto.ModifyCategoryRequest;
import org.example.fourdollar.category.adapter.in.web.dto.RegisterCategoryRequest;
import org.example.fourdollar.category.adapter.in.web.dto.RegisterCategoryResponse;
import org.example.fourdollar.category.application.port.in.GetCategoryUseCase;
import org.example.fourdollar.category.application.port.in.ModifyCategoryUseCase;
import org.example.fourdollar.category.application.port.in.RegisterCategoryUseCase;
import org.example.fourdollar.category.application.port.in.dto.CategoryDetail;
import org.example.fourdollar.category.application.port.in.dto.ModifyCategoryCommand;
import org.example.fourdollar.category.application.port.in.dto.RegisterCategoryCommand;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
class CategoryRestController {

    private final RegisterCategoryUseCase registerCategoryUseCase;
    private final ModifyCategoryUseCase modifyCategoryUseCase;
    private final GetCategoryUseCase getCategoryUseCase;

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
                categoryId,
                new ModifyCategoryCommand(
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


}
