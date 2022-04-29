package com.waazon.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waazon.backend.domains.Product;
import com.waazon.backend.domains.Review;
import com.waazon.backend.services.SellerService;
import com.waazon.backend.services.MyUserDetails.LoginUserDetails;
import com.waazon.backend.services.ProductsService;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {
    ProductsService productsService;
    SellerService sellerService;

    @Autowired
    public ProductController(ProductsService productsService, SellerService sellerService) {
        this.productsService = productsService;
        this.sellerService = sellerService;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails userDetails = (LoginUserDetails) auth.getPrincipal();

        product.setSeller(sellerService.getSellerByUserName(userDetails.getUsername()));
        return productsService.addProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        return productsService.getProductById(id).orElse(null);
    }

    @GetMapping()
    public List<Product> getAllProductsPaging(@RequestParam(required = false, name = "page") String page,
                                              @RequestParam(required = false, name = "limit") String limit,
                                              @RequestParam(required = false, name = "cat") String categoryId,
                                              @RequestParam(required = false, name = "userName") String sellerUserName) {
        if (null != page && null != limit) {
            if (null != categoryId)
                return productsService.getAllProductsWithCategories(
                        PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit)), Integer.parseInt(categoryId));
            else if (null != sellerUserName)
                return productsService.getProductsBySeller(
                        PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit)), sellerUserName);
            else
                return productsService.getAllProductsWithPagingAndSorting(
                        PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit)));

        } else if (null != sellerUserName)
            return productsService.getProductsBySeller(Pageable.unpaged(), sellerUserName);
        else if (null != categoryId)
            return productsService.getAllProductsWithCategories(Pageable.unpaged(), Integer.parseInt(categoryId));

        else
            return productsService.getAllProducts(Pageable.unpaged());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        productsService.deleteProduct(id);
    }

    @GetMapping("/{id}/reviews")
    public List<Review> getAllReviews(@PathVariable long id) {
        return productsService.getAllReviewsByProductId(id);
    }

    @PutMapping("/{id}")
    public Product editProduct(@PathVariable long id, @RequestBody Product product) {
        return productsService.editProduct(id, product);
    }

    @PostMapping("/{id}/reviews")
    public Product addReview(@RequestBody Review review, @PathVariable long id) {
        return productsService.addProductReview(id, review);
    }
}
