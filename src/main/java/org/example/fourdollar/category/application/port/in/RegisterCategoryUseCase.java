package org.example.fourdollar.category.application.port.in;

import org.example.fourdollar.category.application.port.in.dto.RegisterCategoryCommand;
import org.jetbrains.annotations.NotNull;

public interface RegisterCategoryUseCase {

    @NotNull Long registerCategory(@NotNull RegisterCategoryCommand command);

}
