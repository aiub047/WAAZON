package com.waazon.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.waazon.backend.domains.Product;
import com.waazon.backend.domains.Review;

@Repository
public interface ProductsRepo extends PagingAndSortingRepository<Product, Long> {

    @Query(value = "select prod from Product prod where prod.id=:id")
    Optional<Product> getProductById(long id);

    @Query(value = "select p.reviews from Product p where p.id=:productID")
    List<Review> findAllReviewsForProduct(long productID);

    //@Query(value = "select review from Review review where review.status=:status", nativeQuery = true)
    @Query(value = "select review from Review review where review.status=:status")
    List<Review> getAllReviewsWithoutApproval(String status);
}
