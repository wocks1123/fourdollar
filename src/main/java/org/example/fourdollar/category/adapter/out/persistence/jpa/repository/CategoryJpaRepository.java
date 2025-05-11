package org.example.fourdollar.category.adapter.out.persistence.jpa.repository;

import org.example.fourdollar.category.adapter.out.persistence.jpa.entity.CategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryJpaEntity, Long> {
}
