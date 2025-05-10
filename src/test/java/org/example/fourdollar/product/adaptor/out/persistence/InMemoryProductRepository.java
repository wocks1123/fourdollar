package org.example.fourdollar.product.adaptor.out.persistence;

import lombok.SneakyThrows;
import org.example.fourdollar.product.application.port.out.SaveProductPort;
import org.example.fourdollar.product.domain.Product;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryProductRepository implements SaveProductPort {

    private final Map<Long, Product> productStore = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);


    @Override
    @SneakyThrows
    public Product save(Product product) {
        Long id = idGenerator.incrementAndGet();
        Field idField = Product.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(product, id);
        productStore.put(id, product);
        return product;
    }

}
