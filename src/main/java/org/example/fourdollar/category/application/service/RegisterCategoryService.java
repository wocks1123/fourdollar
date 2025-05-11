package org.example.fourdollar.category.application.service;

import lombok.RequiredArgsConstructor;
import org.example.fourdollar.category.application.port.in.RegisterCategoryUseCase;
import org.example.fourdollar.category.application.port.in.dto.RegisterCategoryCommand;
import org.example.fourdollar.category.application.port.out.LoadCategoryPort;
import org.example.fourdollar.category.application.port.out.SaveCategoryPort;
import org.example.fourdollar.category.domain.Category;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterCategoryService implements RegisterCategoryUseCase {

    private final SaveCategoryPort saveCategoryPort;
    private final LoadCategoryPort loadCategoryPort;


    @Override
    public @NotNull Long registerCategory(@NotNull RegisterCategoryCommand command) {
        Category newCategory = new Category(
                command.name(),
                command.description(),
                command.displayOrder()
        );

        if (command.parentId() != null) {
            Category parentCategory = loadCategoryPort.findBy(command.parentId())
                    .orElseThrow(() -> new IllegalArgumentException("부모 카테고리가 존재하지 않습니다. : " + command.parentId()));
            parentCategory.addSubCategory(newCategory);
        }

        Category savedCategory = saveCategoryPort.save(newCategory);
        return savedCategory.getId();
    }

}
