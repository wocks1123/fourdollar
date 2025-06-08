package com.example.fourdollardomain.category.application.port.in;

import com.example.fourdollardomain.category.application.port.in.dto.CategoryDetail;
import org.jetbrains.annotations.NotNull;

public interface GetCategoryUseCase {

    @NotNull CategoryDetail getCategory(@NotNull Long categoryId);

}
