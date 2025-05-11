package org.example.fourdollar.category.application.port.out;

import org.example.fourdollar.category.domain.Category;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface LoadCategoryPort {

    Optional<Category> findBy(@NotNull Long categoryId);

}
