package com.example.fourdollardomain.product.application.port.in;

import com.example.fourdollardomain.product.application.port.in.dto.RegisterProductCommand;
import org.jetbrains.annotations.NotNull;

public interface RegisterProductUseCase {

    @NotNull Long registerProduct(@NotNull RegisterProductCommand command);

}
