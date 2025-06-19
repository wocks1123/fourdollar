package com.example.fourdollardomain.product.application.service;

import com.example.fourdollardomain.category.application.port.out.LoadCategoryPort;
import com.example.fourdollardomain.common.exception.FdIllegalArgumentException;
import com.example.fourdollardomain.product.application.port.in.RegisterProductUseCase;
import com.example.fourdollardomain.product.application.port.in.dto.RegisterProductCommand;
import com.example.fourdollardomain.product.application.port.out.SaveProductPort;
import com.example.fourdollardomain.product.domain.Product;
import com.example.fourdollardomain.product.domain.ProductCategory;
import com.example.fourdollardomain.product.domain.ProductOption;
import com.example.fourdollardomain.product.domain.ProductOptionGroup;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterProductService implements RegisterProductUseCase {

    private final SaveProductPort saveProductPort;
    private final LoadCategoryPort loadCategoryPort;


    @Override
    public @NotNull Product registerProduct(@NotNull RegisterProductCommand command) {
        Product product = Product.builder()
                .productCode(command.productCode())
                .name(command.name())
                .slug(command.slug())
                .shortDescription(command.shortDescription())
                .fullDescription(command.fullDescription())
                .basePrice(command.basePrice())
                .saleStartDate(command.saleStartDate())
                .saleEndDate(command.saleEndDate())
                .optionGroups(command.optionGroups().stream()
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
                        .toList())
                .build();

        if (loadCategoryPort.countByIdIn(command.categoryIds()) != command.categoryIds().size()) {
            throw new FdIllegalArgumentException("Some categories do not exist.");
        }

        product.addCategories(command.categoryIds().stream()
                .map(id -> ProductCategory.builder().categoryId(id).build())
                .toList());

        return saveProductPort.saveProduct(product);
    }

}
