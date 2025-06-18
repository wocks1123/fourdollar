package com.example.fourdollardomain.category.infra;

import com.example.fourdollardomain.category.application.port.out.LoadCategoryListPort;
import com.example.fourdollardomain.category.application.port.out.LoadCategoryPort;
import com.example.fourdollardomain.category.application.port.out.SaveCategoryPort;
import com.example.fourdollardomain.category.domain.Category;
import com.example.fourdollardomain.category.infra.persistence.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements LoadCategoryPort, SaveCategoryPort, LoadCategoryListPort {

    private final CategoryJpaRepository categoryJpaRepository;


    @Override
    public Optional<Category> findBy(@NotNull Long id) {
        return categoryJpaRepository.findById(id);
    }

    @Override
    public @NotNull Category save(@NotNull Category category) {
        return categoryJpaRepository.save(category);
    }

    @Override
    public List<Category> findAllRootCategories() {
        return categoryJpaRepository.findAllRootCategories();
    }

}
