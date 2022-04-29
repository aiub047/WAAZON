package com.waazon.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.waazon.backend.domains.Admin;
import com.waazon.backend.domains.Product;
import com.waazon.backend.domains.ProductCategory;
import com.waazon.backend.domains.Review;
import com.waazon.backend.domains.Seller;
import com.waazon.backend.services.SellerService;
import com.waazon.backend.services.AdminService;
import com.waazon.backend.services.CategoryService;
import com.waazon.backend.services.ProductsService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = {"*"})
public class AdminController {
    ProductsService productsService;
    SellerService sellerService;
    AdminService adminService;
    CategoryService categoryService;

    public AdminController() {
    }

    @Autowired
    public AdminController(ProductsService productsService, SellerService sellerService, AdminService adminService, CategoryService categoryService) {
        this.productsService = productsService;
        this.sellerService = sellerService;
        this.adminService = adminService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products/{id}")
    public Product approveProduct(@RequestParam("approved") boolean approved, @PathVariable long id) {
        if (approved) return productsService.approveOrReject(id, "Approved");
        else return productsService.approveOrReject(id, "Rejected");
    }

    @GetMapping("{admin_userName}/profile")
    public Admin getAdminProfile(@PathVariable String admin_userName) {
        return adminService.getAdminProfile(admin_userName);
    }

    @GetMapping("/products/{product_id}/reviews/{review_id}")
    public Review approveReview(@PathVariable long product_id, @PathVariable long review_id, @RequestParam("approved") boolean approved) {
        if (approved) return productsService.approveReview(product_id, review_id, "Approved");
        else return productsService.approveReview(product_id, review_id, "Rejected");
    }

    @GetMapping("/seller/{id}")
    public Seller approveSeller(@RequestParam("approved") boolean approved, @PathVariable Long id) {
        if (approved) return sellerService.approveOrReject(id, "Approved");
        else return sellerService.approveOrReject(id, "Rejected");
    }

    @GetMapping("/reviews")
    public List<Review> getNonApprovedReviews() {
        return productsService.getNonApprovedReviews();
    }

    @PostMapping("/cats")
    public ProductCategory addCategory(@RequestBody ProductCategory productCategory) {
        return categoryService.addCategory(productCategory);
    }

    @PutMapping("/cats/{id}")
    public ProductCategory editCat(@RequestBody ProductCategory productCategory, @PathVariable long id) {
        return categoryService.editCategory(id, productCategory);
    }

    @GetMapping("/sellers")
    public List<Seller> getAllSellers() {
        return sellerService.getAllSellers();
    }

    @GetMapping("/sellers/{id}")
    public Seller getOneSeller(@PathVariable int id) {
        return sellerService.getSellerById(id);
    }

    @DeleteMapping("/cats/{id}")
    public String deleteCat(@PathVariable long id) {
        return categoryService.deleteCategory(id);
    }
}
