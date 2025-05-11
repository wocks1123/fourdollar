package org.example.fourdollar.category.application.port.out;

import org.jetbrains.annotations.NotNull;

public interface ExistCategoryPort {

    boolean exists(@NotNull Long categoryId);

}
