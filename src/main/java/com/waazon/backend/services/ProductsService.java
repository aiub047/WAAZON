package com.waazon.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.waazon.backend.domains.Product;
import com.waazon.backend.domains.Review;

public interface ProductsService {
    Product addProduct(Product product);

    List<Product> getAllProducts(Pageable pageable);

    Optional<Product> getProductById(long id);

    boolean isExistById(long id);

    List<Product> getProductsBySeller(Pageable pageable, String SellerUserName);

    Product editProduct(long id, Product product);

    String deleteProductById(long id);

    void deleteProduct(long product_id);

    Product approveOrReject(long id, String status);

    List<Review> getAllReviewsByProductId(long id);

    Product addProductReview(long id, Review review);

    Review approveReview(long product_id, long review_id, String approved);

    List<Product> getAllProductsWithPagingAndSorting(Pageable pageable);

    List<Review> getNonApprovedReviews();

    List<Product> getAllProductsWithCategories(Pageable pageable, long cat_ID);
}
