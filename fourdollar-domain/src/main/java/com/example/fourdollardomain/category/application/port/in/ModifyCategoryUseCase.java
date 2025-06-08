package com.example.fourdollardomain.category.application.port.in;

import com.example.fourdollardomain.category.application.port.in.dto.ModifyCategoryCommand;
import org.jetbrains.annotations.NotNull;

public interface ModifyCategoryUseCase {

    void modifyCategory(@NotNull ModifyCategoryCommand command);

}
