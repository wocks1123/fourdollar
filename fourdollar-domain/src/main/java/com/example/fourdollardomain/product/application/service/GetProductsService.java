package com.example.fourdollardomain.product.application.service;

import com.example.fourdollardomain.product.application.port.in.GetProductListUseCase;
import com.example.fourdollardomain.product.application.port.in.dto.ProductSearchCommand;
import com.example.fourdollardomain.product.application.port.in.dto.ProductSearchResult;
import com.example.fourdollardomain.product.application.port.out.LoadProductsPort;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetProductsService implements GetProductListUseCase {

    private final LoadProductsPort loadProductsPort;


    @Override
    public @NotNull ProductSearchResult getProducts(@NotNull ProductSearchCommand command) {
        return loadProductsPort.findProducts(command.page(), command.size());
    }

}
