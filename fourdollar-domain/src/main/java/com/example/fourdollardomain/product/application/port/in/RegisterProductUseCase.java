package com.example.fourdollardomain.product.application.port.in;

import com.example.fourdollardomain.product.application.port.in.dto.RegisterProductCommand;
import com.example.fourdollardomain.product.domain.Product;
import org.jetbrains.annotations.NotNull;

public interface RegisterProductUseCase {

    @NotNull Product registerProduct(@NotNull RegisterProductCommand command);

}
