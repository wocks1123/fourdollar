package com.example.fourdollardomain.category.infra.persistence;

import com.example.fourdollardomain.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryJpaRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.level = 1 ORDER BY c.displayOrder ASC")
    List<Category> findAllRootCategories();

}
