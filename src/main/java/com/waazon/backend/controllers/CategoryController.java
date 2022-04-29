package com.waazon.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.waazon.backend.domains.ProductCategory;
import com.waazon.backend.services.CategoryService;
import com.waazon.backend.services.ProductsService;

import java.util.List;

@RestController
@RequestMapping("/cats")
@CrossOrigin(origins = {"*"})
public class CategoryController {
    CategoryService categoryService;
    ProductsService poProductsService;

    @Autowired
    public CategoryController(CategoryService categoryService, ProductsService poProductsService) {
        this.categoryService = categoryService;
        this.poProductsService = poProductsService;
    }

    @GetMapping("{cat_id}")
    public ProductCategory getOneCat(@PathVariable long cat_id) {
        return categoryService.getOneCat(cat_id).orElse(null);
    }

    @GetMapping()
    public List<ProductCategory> getAllCats() {
        return categoryService.getAllCategories();
    }
}
