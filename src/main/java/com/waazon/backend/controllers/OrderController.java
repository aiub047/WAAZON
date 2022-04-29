package com.waazon.backend.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waazon.backend.domains.Order;
import com.waazon.backend.services.BuyerService;
import com.waazon.backend.services.OrderService;
import com.waazon.backend.services.SellerService;
import com.waazon.backend.services.MyUserDetails.LoginUserDetails;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = {"*"})
public class OrderController {
    OrderService orderService;
    BuyerService buyerService;
    SellerService sellerService;

    @Autowired
    public OrderController(OrderService orderService, BuyerService buyerService, SellerService sellerService) {
        this.orderService = orderService;
        this.buyerService = buyerService;
        this.sellerService = sellerService;
    }

    @GetMapping
    public List<Order> getOrders() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails userDetails = (LoginUserDetails) auth.getPrincipal();

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        boolean isSeller = authorities.contains(new SimpleGrantedAuthority("SELLER"));

        if (isSeller) {
            return null;
        }

        return orderService.findOrdersByBuyerId(buyerService.getBuyerByUsername(userDetails.getUsername()).getBId());
    }

    @PostMapping
    public void addOrder(@RequestBody Order order) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails userDetails = (LoginUserDetails) auth.getPrincipal();

        order.setBuyer(buyerService.getBuyerByUsername(userDetails.getUsername()));
        order.setPrice(order.getLineItems().stream().mapToDouble(e -> e.getPrice() * e.getQuantity()).sum());

        orderService.save(order);
    }
}
