package com.example.fourdollardomain.product.infra.persistence;

import com.example.fourdollardomain.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {
}
