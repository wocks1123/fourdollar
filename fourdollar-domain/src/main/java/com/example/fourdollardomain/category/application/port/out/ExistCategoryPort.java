package com.example.fourdollardomain.category.application.port.out;

import org.jetbrains.annotations.NotNull;

public interface ExistCategoryPort {

    boolean exists(@NotNull Long categoryId);

}
