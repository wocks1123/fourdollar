package com.example.fourdollardomain.product.application.service;

import com.example.fourdollardomain.product.application.port.in.RegisterProductUseCase;
import com.example.fourdollardomain.product.application.port.in.dto.RegisterProductCommand;
import com.example.fourdollardomain.product.application.port.out.SaveProductPort;
import com.example.fourdollardomain.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
class RegisterProductService implements RegisterProductUseCase {

    private final SaveProductPort saveProductPort;


    @Override
    public @NotNull Long registerProduct(@NotNull RegisterProductCommand command) {
        Product product = Product.builder()
                .productCode(command.productCode())
                .name(command.name())
                .slug(command.slug())
                .shortDescription(command.shortDescription())
                .fullDescription(command.fullDescription())
                .basePrice(command.basePrice())
                .saleStartDate(command.saleStartDate())
                .saleEndDate(command.saleEndDate())
                .build();

        return saveProductPort.saveProduct(product).getId();
    }

}
