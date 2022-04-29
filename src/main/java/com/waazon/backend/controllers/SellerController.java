package com.waazon.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waazon.backend.domains.Order;
import com.waazon.backend.domains.Product;
import com.waazon.backend.domains.Seller;
import com.waazon.backend.services.OrderService;
import com.waazon.backend.services.SellerService;
import com.waazon.backend.services.ProductsService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/seller")
@SuppressWarnings("unused")
public class SellerController {

    final ProductsService productsService;
    final SellerService sellerService;
    final OrderService orderService;

    @Autowired
    public SellerController(ProductsService productsService, SellerService sellerService, OrderService orderService) {
        this.productsService = productsService;
        this.sellerService = sellerService;
        this.orderService = orderService;
    }

    @GetMapping("/{seller_userName}")
    private Seller getSeller(@PathVariable String seller_userName) {
        return sellerService.getSellerByUserName(seller_userName);
    }

    @PostMapping
    public void addSeller(@RequestBody Seller seller) {
        sellerService.addSeller(seller);
    }

    @GetMapping
    public List<Seller> getSellers() {
        return sellerService.getAllSellers();
    }

    @PostMapping("/{userName}/order/cancel/{id}")
    public Order cancelOrder(@PathVariable("userName") String userName, @PathVariable("id") long oId) {
        return sellerService.cancelSellerOrder(userName, oId);
    }

    @GetMapping("/{userName}/orders")
    public List<Order> getOrders(@PathVariable("userName") String userName) {
        return sellerService.getOrdersBySellerId(sellerService.getSellerByUserName(userName).getId());
    }

    @GetMapping("/{userName}/order/{id}")
    public Order getOrderById(@PathVariable("userName") String userName, @PathVariable("id") long oId) {
        return sellerService.getOrderByIdForSeller(userName, oId);
    }

    @PostMapping("/{userName}/order/ontheway/{id}")
    public Order onTheWayOrder(@PathVariable("userName") String userName, @PathVariable("id") long oId) {
        return sellerService.onTheWaySellerOrder(userName, oId);
    }

    @PostMapping("/{userName}/order/shipped/{id}")
    public Order shipOrder(@PathVariable("userName") String userName, @PathVariable("id") long oId) {
        return sellerService.shipSellerOrder(userName, oId);
    }

    @PostMapping("/{userName}/order/{id}/{status}")
    public Order orderStatus(@PathVariable("userName") String userName, @PathVariable("id") long oId, @PathVariable int status) {
        return sellerService.deliveredSellerOrder(userName, oId);
    }

    @PostMapping("/{userName}/order/delivered/{id}")
    public Order deliveredOrder(@PathVariable("userName") String userName, @PathVariable("id") long oId) {
        return sellerService.deliveredSellerOrder(userName, oId);
    }

    @DeleteMapping("{seller_userName}/products/{product_id}")
    public void deleteProduct(@PathVariable long product_id, @PathVariable String seller_userName) {
        productsService.deleteProduct(product_id);
    }

    @PostMapping("/{userName}")
    public Product addProduct(@RequestBody Product product, @RequestParam("cat") long cat_id,
                              @PathVariable String userName) {
        return productsService.addProduct(product);
    }
}
