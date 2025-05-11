package org.example.fourdollar.category.application.port.in;

import org.example.fourdollar.category.application.port.in.dto.ModifyCategoryCommand;
import org.jetbrains.annotations.NotNull;

public interface ModifyCategoryUseCase {

    void modifyCategory(@NotNull Long id, @NotNull ModifyCategoryCommand command);

}
