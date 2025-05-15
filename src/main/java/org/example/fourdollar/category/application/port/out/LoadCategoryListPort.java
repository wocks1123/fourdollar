package org.example.fourdollar.category.application.port.out;

import org.example.fourdollar.category.domain.Category;

import java.util.List;

public interface LoadCategoryListPort {

    List<Category> findAllRootCategories();

}
