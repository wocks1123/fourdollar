package org.example.fourdollar.category.application.service;

import lombok.RequiredArgsConstructor;
import org.example.fourdollar.category.application.port.in.ModifyCategoryUseCase;
import org.example.fourdollar.category.application.port.in.dto.ModifyCategoryCommand;
import org.example.fourdollar.category.application.port.out.LoadCategoryPort;
import org.example.fourdollar.category.application.port.out.SaveCategoryPort;
import org.example.fourdollar.category.domain.Category;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyCategoryService implements ModifyCategoryUseCase {

    private final LoadCategoryPort loadCategoryPort;
    private final SaveCategoryPort saveCategoryPort;


    @Override
    public void modifyCategory(@NotNull Long categoryId, @NotNull ModifyCategoryCommand command) {
        Category category = loadCategoryPort.findBy(categoryId)
                .orElseThrow(() -> new IllegalIdentifierException("카테고리가 존재하지 않습니다. : " + categoryId));

        category.modify(
                command.name(),
                command.description(),
                command.displayOrder()
        );

        saveCategoryPort.save(category);
    }

}
