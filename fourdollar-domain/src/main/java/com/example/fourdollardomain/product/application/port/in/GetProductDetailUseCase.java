package com.example.fourdollardomain.product.application.port.in;

import com.example.fourdollardomain.product.application.port.in.dto.ProductDetail;
import org.jetbrains.annotations.NotNull;

public interface GetProductDetailUseCase {

    @NotNull ProductDetail getProductDetail(Long productId);

}
