package org.example.fourdollar.category.application.service;

import org.example.fourdollar.category.adaptor.out.persistence.CategoryInMemoryRepository;
import org.example.fourdollar.category.application.port.in.dto.RegisterCategoryCommand;
import org.example.fourdollar.category.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("카테고리 등록 테스트")
class RegisterCategoryServiceTest {

    private RegisterCategoryService registerCategoryService;
    private CategoryInMemoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryRepository = new CategoryInMemoryRepository();
        registerCategoryService = new RegisterCategoryService(
                categoryRepository,
                categoryRepository
        );
    }

    @Test
    @DisplayName("카테고리를 정상적으로 등록한다.")
    void test01() {
        // given
        RegisterCategoryCommand command = new RegisterCategoryCommand(
                null,
                "Test Category",
                "Test Description",
                1
        );

        // when
        Long categoryId = registerCategoryService.registerCategory(command);

        // then
        assertThat(categoryId).isNotNull();
        Category foundCategory = categoryRepository.findBy(categoryId).orElse(null);
        assertThat(foundCategory).isNotNull();
        assertAll(
                () -> assertThat(foundCategory.getId()).isEqualTo(categoryId),
                () -> assertThat(foundCategory.getParent()).isNull(),
                () -> assertThat(foundCategory.getName()).isEqualTo(command.name()),
                () -> assertThat(foundCategory.getDescription()).isEqualTo(command.description()),
                () -> assertThat(foundCategory.getLevel()).isEqualTo(1),
                () -> assertThat(foundCategory.getDisplayOrder()).isEqualTo(command.displayOrder())
        );
    }

    @Test
    @DisplayName("부모 카테고리가 존재하지 않으면 예외가 발생한다.")
    void test02() {
        // given
        RegisterCategoryCommand command = new RegisterCategoryCommand(
                999L, // 존재하지 않는 부모 카테고리 ID
                "Test Category",
                "Test Description",
                1
        );

        // when & then
        assertThatThrownBy(() -> registerCategoryService.registerCategory(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("부모 카테고리가 존재하지 않습니다. : 999");
    }

    @Test
    @DisplayName("등록 시 부모 카테고리를 지정할 수 있다.")
    void test03() {
        // given
        Long parentId = registerCategoryService.registerCategory(new RegisterCategoryCommand(
                null,
                "Parent Category",
                "Parent Description",
                1
        ));

        RegisterCategoryCommand command = new RegisterCategoryCommand(
                parentId,
                "Child Category",
                "Child Description",
                2
        );

        // when
        Long childCategoryId = registerCategoryService.registerCategory(command);

        // then
        assertThat(childCategoryId).isNotNull();
        Category foundChildCategory = categoryRepository.findBy(childCategoryId).orElse(null);
        assertThat(foundChildCategory).isNotNull();
        assertAll(
                () -> assertThat(foundChildCategory.getId()).isEqualTo(childCategoryId),
                () -> assertThat(foundChildCategory.getParent().getId()).isEqualTo(parentId),
                () -> assertThat(foundChildCategory.getName()).isEqualTo(command.name()),
                () -> assertThat(foundChildCategory.getDescription()).isEqualTo(command.description()),
                () -> assertThat(foundChildCategory.getLevel()).isEqualTo(2),
                () -> assertThat(foundChildCategory.getDisplayOrder()).isEqualTo(command.displayOrder())
        );

        Category parentCategory = categoryRepository.findBy(parentId).orElse(null);
        assertThat(parentCategory).isNotNull();
        assertThat(parentCategory.getSubCategories()).contains(foundChildCategory);
    }

}
