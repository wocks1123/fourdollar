package com.example.fourdollardomain.category.application.port.in;

import com.example.fourdollardomain.category.application.port.in.dto.RegisterCategoryCommand;
import org.jetbrains.annotations.NotNull;

public interface RegisterCategoryUseCase {

    @NotNull Long registerCategory(@NotNull RegisterCategoryCommand command);

}
