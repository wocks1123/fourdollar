package org.example.fourdollar.category.application.service;

import org.example.fourdollar.category.adaptor.out.persistence.CategoryInMemoryRepository;
import org.example.fourdollar.category.application.port.in.dto.ModifyCategoryCommand;
import org.example.fourdollar.category.application.port.in.dto.RegisterCategoryCommand;
import org.example.fourdollar.category.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ModifyCategoryServiceTest {

    private ModifyCategoryService modifyCategoryService;
    private CategoryInMemoryRepository categoryRepository;
    private RegisterCategoryService registerCategoryService;

    @BeforeEach
    void setUp() {
        categoryRepository = new CategoryInMemoryRepository();
        modifyCategoryService = new ModifyCategoryService(
                categoryRepository,
                categoryRepository
        );
        registerCategoryService = new RegisterCategoryService(
                categoryRepository,
                categoryRepository
        );
    }

    @Test
    @DisplayName("카테고리의 기본 정보를 수정한다.")
    void test01() {
        // given
        Long categoryId = registerCategoryService.registerCategory(new RegisterCategoryCommand(
                null,
                "Test Category",
                "Test Description",
                1
        ));
        ModifyCategoryCommand command = new ModifyCategoryCommand(
                "Updated Category",
                "Updated Description",
                2
        );

        // when
        modifyCategoryService.modifyCategory(categoryId, command);

        // then
        Category modifiedCategory = categoryRepository.findBy(categoryId).orElse(null);
        assertThat(modifiedCategory).isNotNull();
        assertAll(
                () -> assertThat(modifiedCategory.getId()).isEqualTo(categoryId),
                () -> assertThat(modifiedCategory.getName()).isEqualTo(command.name()),
                () -> assertThat(modifiedCategory.getDescription()).isEqualTo(command.description()),
                () -> assertThat(modifiedCategory.getDisplayOrder()).isEqualTo(command.displayOrder())
        );

    }

}
