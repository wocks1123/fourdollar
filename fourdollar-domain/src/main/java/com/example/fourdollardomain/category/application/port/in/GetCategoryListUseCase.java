package com.example.fourdollardomain.category.application.port.in;

import com.example.fourdollardomain.category.application.port.in.dto.CategoryListResponse;

import java.util.List;

public interface GetCategoryListUseCase {

    List<CategoryListResponse> getCategoryList();

}
