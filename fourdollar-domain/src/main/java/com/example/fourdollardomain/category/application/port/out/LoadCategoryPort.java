package com.example.fourdollardomain.category.application.port.out;

import com.example.fourdollardomain.category.domain.Category;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface LoadCategoryPort {

    Optional<Category> findBy(@NotNull Long categoryId);

    long countByIdIn(@NotNull List<Long> ids);

}
