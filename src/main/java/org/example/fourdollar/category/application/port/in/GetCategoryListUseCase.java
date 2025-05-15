package org.example.fourdollar.category.application.port.in;

import org.example.fourdollar.category.application.port.in.dto.CategoryListResponse;

import java.util.List;

public interface GetCategoryListUseCase {

    List<CategoryListResponse> getCategoryList();

}
