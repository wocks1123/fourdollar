package com.example.fourdollardomain.category.application.port.out;

import com.example.fourdollardomain.category.domain.Category;

import java.util.List;

public interface LoadCategoryListPort {

    List<Category> findAllRootCategories();

}
