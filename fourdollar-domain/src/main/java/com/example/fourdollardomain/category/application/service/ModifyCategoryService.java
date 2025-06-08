package com.example.fourdollardomain.category.application.service;

import com.example.fourdollardomain.category.application.port.in.ModifyCategoryUseCase;
import com.example.fourdollardomain.category.application.port.in.dto.ModifyCategoryCommand;
import com.example.fourdollardomain.category.application.port.out.LoadCategoryPort;
import com.example.fourdollardomain.category.application.port.out.SaveCategoryPort;
import com.example.fourdollardomain.category.domain.Category;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyCategoryService implements ModifyCategoryUseCase {

    private final LoadCategoryPort loadCategoryPort;
    private final SaveCategoryPort saveCategoryPort;


    @Override
    public void modifyCategory(@NotNull ModifyCategoryCommand command) {
        Category category = loadCategoryPort.findBy(command.categoryId())
                .orElseThrow(() -> new IllegalIdentifierException("카테고리가 존재하지 않습니다. : " + command.categoryId()));

        category.modify(
                command.name(),
                command.description(),
                command.displayOrder()
        );

        saveCategoryPort.save(category);
    }

}
