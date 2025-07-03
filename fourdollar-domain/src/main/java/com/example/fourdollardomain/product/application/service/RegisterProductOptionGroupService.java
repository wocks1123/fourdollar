package com.example.fourdollardomain.product.application.service;

import com.example.fourdollardomain.common.exception.FdNotFoundException;
import com.example.fourdollardomain.product.application.port.in.RegisterProductOptionGroupUseCase;
import com.example.fourdollardomain.product.application.port.in.dto.RegisterProductOptionGroupCommand;
import com.example.fourdollardomain.product.application.port.out.FindProductsPort;
import com.example.fourdollardomain.product.application.port.out.SaveProductPort;
import com.example.fourdollardomain.product.domain.Product;
import com.example.fourdollardomain.product.domain.ProductOption;
import com.example.fourdollardomain.product.domain.ProductOptionGroup;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterProductOptionGroupService implements RegisterProductOptionGroupUseCase {

    private final FindProductsPort findProductsPort;
    private final SaveProductPort saveProductPort;


    @Override
    public void registerProductOptionGroup(@NotNull RegisterProductOptionGroupCommand command) {
        Product product = findProductsPort.findById(command.productId())
                .orElseThrow(FdNotFoundException::new);

        product.addOptionGroups(
                command.optionGroups().stream()
                        .map(optionGroup -> ProductOptionGroup.builder()
                                .name(optionGroup.name())
                                .displayOrder(optionGroup.displayOrder())
                                .options(optionGroup.options().stream()
                                        .map(option -> ProductOption.builder()
                                                .name(option.name())
                                                .sku(option.sku())
                                                .displayOrder(option.displayOrder())
                                                .additionalPrice(option.additionalPrice())
                                                .build())
                                        .toList())
                                .build())
                        .toList()
        );

        saveProductPort.saveProduct(product);
    }

}
