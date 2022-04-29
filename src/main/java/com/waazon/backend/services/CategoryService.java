package com.waazon.backend.services;

import java.util.List;
import java.util.Optional;

import com.waazon.backend.domains.ProductCategory;

@SuppressWarnings("unused")
public interface CategoryService {
    ProductCategory addCategory(ProductCategory productCategory);

    String deleteCategory(long id);

    ProductCategory editCategory(long id, ProductCategory productCategory);

    List<ProductCategory> getAllCategories();

    Optional<ProductCategory> getOneCat(long cat_id);

    boolean isExistById(long id);
}
