package org.example.fourdollar.category.adapter.out.persistence;

import org.example.fourdollar.category.adapter.out.persistence.jpa.entity.CategoryJpaEntity;
import org.example.fourdollar.category.adapter.out.persistence.jpa.repository.CategoryJpaRepository;
import org.example.fourdollar.category.domain.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(CategoryPersistenceAdapter.class)
@ActiveProfiles("test")
class CategoryPersistenceAdapterTest {

    @Autowired
    private CategoryPersistenceAdapter categoryPersistenceAdapter;
    @Autowired
    private CategoryJpaRepository categoryJpaRepository;


    @Test
    @DisplayName("카테고리 저장 테스트")
    void test01() {
        // given
        Category newCategory = new Category(
                null, // 새로운 카테고리는 ID가 없음
                "새 카테고리",
                "새 카테고리 설명",
                1,
                2,
                Collections.emptyList()
        );

        // when
        Category savedCategory = categoryPersistenceAdapter.save(newCategory);

        // then
        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getId()).isNotNull(); // ID가 생성되었는지 확인
        assertThat(savedCategory.getName()).isEqualTo("새 카테고리");
        assertThat(savedCategory.getDescription()).isEqualTo("새 카테고리 설명");
        assertThat(savedCategory.getLevel()).isEqualTo(1);
        assertThat(savedCategory.getDisplayOrder()).isEqualTo(2);

        // DB에 실제로 저장되었는지 확인
        Optional<CategoryJpaEntity> entityFromDb = categoryJpaRepository.findById(savedCategory.getId());
        assertThat(entityFromDb).isPresent();
        assertThat(entityFromDb.get().getName()).isEqualTo("새 카테고리");
    }

    @Test
    @DisplayName("자식 카테고리 저장 테스트")
    void test02() {
        // given
        Category parentCategory = new Category(
                "부모 카테고리",
                "부모 카테고리 설명",
                1
        );
        Category savedParentCategory = categoryPersistenceAdapter.save(parentCategory);

        Category childCategory = new Category(
                "자식 카테고리",
                "자식 카테고리 설명",
                1
        );
        savedParentCategory.addSubCategory(childCategory);

        // when
        Category savedChildCategory = categoryPersistenceAdapter.save(childCategory);

        // then
        assertThat(savedChildCategory).isNotNull();
        assertThat(savedChildCategory.getId()).isNotNull(); // ID가 생성되었는지 확인
        assertThat(savedChildCategory.getName()).isEqualTo("자식 카테고리");
        assertThat(savedChildCategory.getDescription()).isEqualTo("자식 카테고리 설명");
        assertThat(savedChildCategory.getLevel()).isEqualTo(2);
        assertThat(savedChildCategory.getDisplayOrder()).isEqualTo(1);

        // DB에 실제로 저장되었는지 확인
        CategoryJpaEntity entityFromDb = categoryJpaRepository.findById(parentCategory.getId()).orElse(null);
        assertThat(entityFromDb).isNotNull();
        assertThat(entityFromDb.getName()).isEqualTo("부모 카테고리");
        assertThat(entityFromDb.getSubCategories()).isNotEmpty();
    }

}
