package com.example.fourdollardomain.category.application.port.out;

import com.example.fourdollardomain.category.domain.Category;
import org.jetbrains.annotations.NotNull;

public interface SaveCategoryPort {

    @NotNull Category save(@NotNull Category category);

}
