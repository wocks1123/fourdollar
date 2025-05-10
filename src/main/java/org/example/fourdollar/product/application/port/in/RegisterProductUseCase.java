package org.example.fourdollar.product.application.port.in;

import org.example.fourdollar.product.application.port.in.dto.RegisterProductCommand;

public interface RegisterProductUseCase {

    Long registerProduct(RegisterProductCommand command);

}
