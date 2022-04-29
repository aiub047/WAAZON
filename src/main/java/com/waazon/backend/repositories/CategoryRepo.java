package com.waazon.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.waazon.backend.domains.ProductCategory;

public interface CategoryRepo extends CrudRepository<ProductCategory,Long> {
}
