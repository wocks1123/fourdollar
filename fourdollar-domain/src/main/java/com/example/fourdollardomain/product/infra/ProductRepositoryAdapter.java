package com.example.fourdollardomain.product.infra;

import com.example.fourdollardomain.product.application.port.in.dto.ProductSearchResult;
import com.example.fourdollardomain.product.application.port.out.LoadProductsPort;
import com.example.fourdollardomain.product.application.port.out.SaveProductPort;
import com.example.fourdollardomain.product.domain.Product;
import com.example.fourdollardomain.product.infra.persistence.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements LoadProductsPort, SaveProductPort {

    private final ProductJpaRepository productJpaRepository;


    @Override
    public @NotNull ProductSearchResult findProducts(int page, int size) {
        var founds = productJpaRepository.findAll(PageRequest.of(page, size));
/*        var products = founds.getContent().stream()
                .map(ProductSummary::from)
                .toList();
        return new ProductSearchResult(
                products,
                founds.getNumber(),
                founds.getTotalPages(),
                founds.getTotalElements(),
                founds.hasNext(),
                founds.hasPrevious()
        );*/
        return new ProductSearchResult(
                null,
                founds.getNumber(),
                founds.getTotalPages(),
                founds.getTotalElements(),
                founds.hasNext(),
                founds.hasPrevious()
        );
    }

    @Override
    public @NotNull Product saveProduct(@NotNull Product product) {
        return productJpaRepository.save(product);
    }
}
