package org.example.fourdollar.category.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.example.fourdollar.category.adapter.out.persistence.jpa.entity.CategoryJpaEntity;
import org.example.fourdollar.category.adapter.out.persistence.jpa.repository.CategoryJpaRepository;
import org.example.fourdollar.category.application.port.out.LoadCategoryListPort;
import org.example.fourdollar.category.application.port.out.LoadCategoryPort;
import org.example.fourdollar.category.application.port.out.SaveCategoryPort;
import org.example.fourdollar.category.domain.Category;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements LoadCategoryPort, SaveCategoryPort, LoadCategoryListPort {

    private final CategoryJpaRepository categoryJpaRepository;


    @Override
    public Optional<Category> findBy(@NotNull Long id) {
        return categoryJpaRepository.findById(id)
                .map(this::mapToDomain);
    }

    @Override
    public @NotNull Category save(@NotNull Category category) {
        return mapToDomain(categoryJpaRepository.save(mapToJpaEntity(category)));
    }

    private Category mapToDomain(CategoryJpaEntity categoryJpaEntity) {
        return new Category(
                categoryJpaEntity.getId(),
                categoryJpaEntity.getParentId(),
                categoryJpaEntity.getName(),
                categoryJpaEntity.getDescription(),
                categoryJpaEntity.getLevel(),
                categoryJpaEntity.getDisplayOrder(),
                new ArrayList<>(categoryJpaEntity.getSubCategories().stream()
                        .map(this::mapToDomain)
                        .toList())
        );
    }

    private CategoryJpaEntity mapToJpaEntity(Category category) {
        return new CategoryJpaEntity(
                category.getId(),
                category.getParentId(),
                category.getName(),
                category.getDescription(),
                category.getLevel(),
                category.getDisplayOrder(),
                new ArrayList<>(category.getSubCategories().stream()
                        .map(this::mapToJpaEntity)
                        .toList())
        );
    }

    @Override
    public List<Category> findAllRootCategories() {
        return categoryJpaRepository.findAllRootCategories().stream()
                .map(this::mapToDomain)
                .toList();
    }
}
