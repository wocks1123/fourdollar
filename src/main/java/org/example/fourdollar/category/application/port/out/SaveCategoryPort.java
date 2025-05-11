package org.example.fourdollar.category.application.port.out;

import org.example.fourdollar.category.domain.Category;
import org.jetbrains.annotations.NotNull;

public interface SaveCategoryPort {

    @NotNull Category save(@NotNull Category category);

}
