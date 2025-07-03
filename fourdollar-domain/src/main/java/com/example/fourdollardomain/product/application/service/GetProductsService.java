package com.example.fourdollardomain.product.application.service;

import com.example.fourdollardomain.common.exception.FdNotFoundException;
import com.example.fourdollardomain.product.application.port.in.GetProductDetailUseCase;
import com.example.fourdollardomain.product.application.port.in.GetProductListUseCase;
import com.example.fourdollardomain.product.application.port.in.dto.ProductDetail;
import com.example.fourdollardomain.product.application.port.in.dto.ProductSearchCommand;
import com.example.fourdollardomain.product.application.port.in.dto.ProductSearchResult;
import com.example.fourdollardomain.product.application.port.out.FindProductsPort;
import com.example.fourdollardomain.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
class GetProductsService implements GetProductListUseCase, GetProductDetailUseCase {

    private final FindProductsPort findProductsPort;


    @Override
    public @NotNull ProductSearchResult getProducts(@NotNull ProductSearchCommand command) {
        return findProductsPort.findProducts(command.page(), command.size());
    }

    @Override
    public @NotNull ProductDetail getProductDetail(Long productId) {
        Product product = findProductsPort.findById(productId)
                .orElseThrow(FdNotFoundException::new);
        return ProductDetail.builder()
                .id(product.getId())
                .productCode(product.getProductCode())
                .name(product.getName())
                .slug(product.getSlug())
                .shortDescription(product.getShortDescription())
                .fullDescription(product.getFullDescription())
                .basePrice(product.getBasePrice())
                .saleStartDate(product.getSaleStartDate())
                .saleEndDate(product.getSaleEndDate())
                .status(product.getStatus())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .optionGroups(
                        product.getOptionGroups().stream()
                                .map(optionGroup -> new ProductDetail.OptionGroupDetail(
                                        optionGroup.getId(),
                                        optionGroup.getName(),
                                        optionGroup.getDisplayOrder(),
                                        optionGroup.getOptions().stream()
                                                .map(option -> new ProductDetail.OptionDetail(
                                                        option.getId(),
                                                        option.getName(),
                                                        option.getSku(),
                                                        option.getDisplayOrder(),
                                                        option.getAdditionalPrice()
                                                ))
                                                .toList()
                                ))
                                .toList()
                )
                .build();
    }
}
