package org.example.fourdollar.product.application.port.out;

import org.example.fourdollar.product.domain.Product;

public interface SaveProductPort {

    Product save(Product product);

}
