package com.waazon.backend.services.Impls;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.waazon.backend.services.ProductsService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.waazon.backend.domains.Product;
import com.waazon.backend.domains.ProductStatus;
import com.waazon.backend.domains.Review;
import com.waazon.backend.repositories.CategoryRepo;
import com.waazon.backend.repositories.ProductsRepo;
import com.waazon.backend.repositories.SellerRepo;

@Service
public class ProductsServiceImpl implements ProductsService {
    ProductsRepo productsRepo;
    CategoryRepo categoryRepo;
    SellerRepo sellerRepo;

    @Override
    public Product addProduct(Product product) {
        product.setCreatedOn(LocalDate.now());
        product.setAvgRating();

        productsRepo.save(product);
        return product;
    }

    public ProductsServiceImpl(ProductsRepo productsRepo, CategoryRepo categoryRepo, SellerRepo sellerRepo) {
        this.productsRepo = productsRepo;
        this.categoryRepo = categoryRepo;
        this.sellerRepo = sellerRepo;
    }

    @Override
    public Optional<Product> getProductById(long id) {
        return productsRepo.getProductById(id);
    }

    @Override
    public List<Product> getProductsBySeller(Pageable pageable, String sellerUserName) {
        return sellerRepo.findAllProductBySellerUserName(sellerUserName, pageable);
    }

    @Override
    public List<Product> getAllProducts(Pageable pageable) {
        return (List<Product>) productsRepo.findAll();
    }

    @Override
    public String deleteProductById(long id) {
        productsRepo.deleteById(id);
        return "";
    }

    @Override
    public boolean isExistById(long id) {
        return productsRepo.existsById(id);
    }

    @Override
    public Product approveOrReject(long id, String status) {
        try {
            if (productsRepo.existsById(id)) {
                Product product = productsRepo.findById(id).orElse(null);
                if (status.equals("Approved")) {
                    assert product != null;
                    product.setStatus(ProductStatus.APPROVED.getProductStatus());
                    return productsRepo.save(product);

                }
                if (status.equals("Rejected")) {
                    assert product != null;
                    product.setStatus(ProductStatus.REJECTED.getProductStatus());
                    return productsRepo.save(product);

                }
                return null;
            }
            return null;
        } catch (IllegalArgumentException e) {
            System.out.println("STATUS ERROR: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Product editProduct(long id, Product product) {
        try {
            product.setCreatedOn(LocalDate.now());
            return productsRepo.save(product);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product addProductReview(long id, Review review) {
        try {
            if (productsRepo.existsById(id)) {
                Product product = productsRepo.getProductById(id).orElse(null);

                review.setProduct(product);
                assert product != null;
                List<Review> reviews = product.getReviews();
                reviews.add(review);
                return productsRepo.save(product);
            }
            return null;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteProduct(long product_id) {
        productsRepo.deleteById(product_id);
    }

    @Override
    public List<Review> getAllReviewsByProductId(long id) {
        try {
            if (productsRepo.existsById(id)) {
                return productsRepo.findAllReviewsForProduct(id).stream()
                        .filter(review -> review.getStatus().equals(ProductStatus.APPROVED.getProductStatus()))
                        .collect(Collectors.toList());
            }
            return null;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Review approveReview(long product_id, long review_id, String approved) {
        try {
            if (productsRepo.existsById(product_id)) {
                Product product = productsRepo.findById(product_id).orElse(null);
                if (approved.equals("Approved")) {
                    assert product != null;
                    List<Review> reviews = product.getReviews();
                    reviews.forEach(review -> {
                        if (review.getId() == review_id) {
                            review.setStatus(ProductStatus.APPROVED.getProductStatus());
                        }
                    });
                    return productsRepo.save(product).getReviews().stream()
                            .filter(review -> review.getId() == review_id).findFirst().orElse(null);
                }
                if (approved.equals("Rejected")) {
                    assert product != null;
                    List<Review> reviews = product.getReviews();
                    reviews.stream().map(review -> {
                        if (review.getId() == review_id) {
                            review.setStatus(ProductStatus.REJECTED.getProductStatus());
                        }
                        return review;
                    });
                    return productsRepo.save(product).getReviews().stream()
                            .filter(review -> review.getId() == review_id).findFirst().orElse(null);

                }
                return null;
            }
            return null;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getAllProductsWithPagingAndSorting(Pageable pageable) {
        return productsRepo.findAll(pageable).stream().collect(Collectors.toList());
    }

    @Override
    public List<Product> getAllProductsWithCategories(Pageable pageable, long cat_ID) {
        return null;
    }

    @Override
    public List<Review> getNonApprovedReviews() {
        return productsRepo.getAllReviewsWithoutApproval(ProductStatus.PENDING.getProductStatus());
    }

}
