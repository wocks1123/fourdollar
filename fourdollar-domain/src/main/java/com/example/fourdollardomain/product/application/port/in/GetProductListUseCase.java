package com.example.fourdollardomain.product.application.port.in;

import com.example.fourdollardomain.product.application.port.in.dto.ProductSearchCommand;
import com.example.fourdollardomain.product.application.port.in.dto.ProductSearchResult;
import org.jetbrains.annotations.NotNull;

public interface GetProductListUseCase {

    @NotNull ProductSearchResult getProducts(@NotNull ProductSearchCommand command);

}
