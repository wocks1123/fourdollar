package com.example.fourdollarapi.ui.product;

import com.example.fourdollarapi.ui.product.dto.RegisterProductOptionGroupRequest;
import com.example.fourdollarapi.ui.product.dto.RegisterProductRequest;
import com.example.fourdollarapi.ui.product.dto.RegisterProductResponse;
import com.example.fourdollardomain.product.application.port.in.GetProductDetailUseCase;
import com.example.fourdollardomain.product.application.port.in.RegisterProductOptionGroupUseCase;
import com.example.fourdollardomain.product.application.port.in.RegisterProductUseCase;
import com.example.fourdollardomain.product.application.port.in.dto.ProductDetail;
import com.example.fourdollardomain.product.application.port.in.dto.RegisterProductCommand;
import com.example.fourdollardomain.product.application.port.in.dto.RegisterProductOptionGroupCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
class ProductRestController {

    private final RegisterProductUseCase registerProductUseCase;
    private final RegisterProductOptionGroupUseCase registerProductOptionGroupUseCase;
    private final GetProductDetailUseCase getProductDetailUseCase;


    @PostMapping
    RegisterProductResponse registerProduct(@RequestBody RegisterProductRequest request) {
        return new RegisterProductResponse(registerProductUseCase.registerProduct(
                new RegisterProductCommand(
                        request.productCode(),
                        request.name(),
                        request.slug(),
                        request.shortDescription(),
                        request.fullDescription(),
                        request.basePrice()
                )
        ));
    }

    @PostMapping("/{productId}/option-groups")
    void registerProductOptionGroup(@PathVariable Long productId,
                                    @RequestBody List<RegisterProductOptionGroupRequest> request) {
        registerProductOptionGroupUseCase.registerProductOptionGroup(
                new RegisterProductOptionGroupCommand(
                        productId,
                        request.stream()
                                .map(group -> new RegisterProductOptionGroupCommand.OptionGroupDto(
                                        group.name(),
                                        group.displayOrder(),
                                        group.options().stream()
                                                .map(option -> new RegisterProductOptionGroupCommand.OptionDto(
                                                        option.name(),
                                                        option.sku(),
                                                        option.displayOrder(),
                                                        option.additionalPrice()
                                                )).toList()
                                )).toList()
                )
        );
    }

    @GetMapping("/{productId}")
    ProductDetail getProductDetail(@PathVariable Long productId) {
        return getProductDetailUseCase.getProductDetail(productId);
    }


    @GetMapping
    List<String> getAllProducts() {
        // TODO implement
        return List.of();
    }

}
