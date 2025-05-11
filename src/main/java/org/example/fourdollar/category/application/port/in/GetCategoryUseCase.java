package org.example.fourdollar.category.application.port.in;

import org.example.fourdollar.category.application.port.in.dto.CategoryDetail;
import org.jetbrains.annotations.NotNull;

public interface GetCategoryUseCase {

    @NotNull CategoryDetail getCategory(@NotNull Long categoryId);

}
