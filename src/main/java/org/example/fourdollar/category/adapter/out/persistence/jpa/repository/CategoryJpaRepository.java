package org.example.fourdollar.category.adapter.out.persistence.jpa.repository;

import org.example.fourdollar.category.adapter.out.persistence.jpa.entity.CategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryJpaRepository extends JpaRepository<CategoryJpaEntity, Long> {

    @Query("SELECT c FROM CategoryJpaEntity c WHERE c.level = 1 ORDER BY c.displayOrder ASC")
    List<CategoryJpaEntity> findAllRootCategories();

}
