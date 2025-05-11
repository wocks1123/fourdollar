package org.example.fourdollar.product.adaptor.out.persistence;

import org.example.fourdollar.product.application.port.out.SaveProductPort;
import org.example.fourdollar.product.domain.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductPersistenceAdapter implements SaveProductPort {
    @Override
    public Product save(Product product) {
        return null;
    }
}
