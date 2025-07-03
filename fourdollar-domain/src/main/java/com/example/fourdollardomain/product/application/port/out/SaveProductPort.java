package com.example.fourdollardomain.product.application.port.out;

import com.example.fourdollardomain.product.domain.Product;
import org.jetbrains.annotations.NotNull;

public interface SaveProductPort {

    @NotNull Product saveProduct(@NotNull Product product);

}
