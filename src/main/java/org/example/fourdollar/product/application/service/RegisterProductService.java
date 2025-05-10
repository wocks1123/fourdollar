package org.example.fourdollar.product.application.service;

import lombok.RequiredArgsConstructor;
import org.example.fourdollar.product.application.port.in.RegisterProductUseCase;
import org.example.fourdollar.product.application.port.in.dto.RegisterProductCommand;
import org.example.fourdollar.product.application.port.out.SaveProductPort;
import org.example.fourdollar.product.domain.Product;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterProductService implements RegisterProductUseCase {

    private final SaveProductPort saveProductPort;

    @Override
    public Long registerProduct(RegisterProductCommand command) {
        Product product = new Product(
                command.name(),
                command.slug(),
                command.shortDescription(),
                command.fullDescription(),
                command.price(),
                command.sellerId(),
                command.brandId()
        );
        return saveProductPort.save(product).getId();
    }

}
