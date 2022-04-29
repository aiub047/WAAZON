package com.waazon.backend.services.Impls;

import com.waazon.backend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waazon.backend.domains.ProductCategory;
import com.waazon.backend.repositories.CategoryRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public ProductCategory addCategory(ProductCategory productCategory) {
        try {
            return categoryRepo.save(productCategory);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductCategory> getAllCategories() {
        try {
            return (List<ProductCategory>) categoryRepo.findAll();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String deleteCategory(long id) {
        try {
            categoryRepo.deleteById(id);
            return "Category Delete.";
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "Category Error: " + e.getMessage();
        }
    }

    @Override
    public ProductCategory editCategory(long id, ProductCategory productCategory) {

        try {
            if (categoryRepo.existsById(id)) {
                ProductCategory oldOne = categoryRepo.findById(id).get();
                if (productCategory.getName() != null) oldOne.setName(productCategory.getName());
                return oldOne;
            } else return null;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isExistById(long id) {
        return categoryRepo.existsById(id);
    }

    @Override
    public Optional<ProductCategory> getOneCat(long cat_id) {
        return categoryRepo.findById(cat_id);
    }
}
