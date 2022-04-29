package com.waazon.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

import com.waazon.backend.domains.Address;
import com.waazon.backend.domains.Buyer;
import com.waazon.backend.domains.CreditCard;
import com.waazon.backend.domains.LineItem;
import com.waazon.backend.domains.Order;
import com.waazon.backend.domains.Seller;
import com.waazon.backend.dtos.OrderDTO;
import com.waazon.backend.services.BuyerService;
import com.waazon.backend.services.OrderService;
import com.waazon.backend.services.SellerService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/buyer")
@SuppressWarnings("unused")
public class BuyerController {
    BuyerService buyerService;
    SellerService sellerService;
    OrderService orderService;

    @Autowired
    public BuyerController(SellerService sellerService, OrderService orderService, BuyerService buyerService) {
        this.sellerService = sellerService;
        this.orderService = orderService;
        this.buyerService = buyerService;
    }

    @PutMapping("/profile/{userName}")
    public void editBuyer(@RequestBody Buyer buyer, @PathVariable String userName) {
        buyerService.editBuyer(userName, buyer);
    }

    @PostMapping
    public void addBuyer(@RequestBody Buyer buyer) {
        buyerService.addBuyer(buyer);
    }

    @GetMapping("/pagination")
    public List<Buyer> getAllBuyers_Paging(@RequestParam("page") int pageNumber) {
        return buyerService.getAllBuyerWithPagingAndSorting(PageRequest.of(pageNumber, 1));
    }

    @GetMapping
    public List<Buyer> getAllBuyer() {
        return buyerService.getAllBuyers();
    }

    @GetMapping("/{userName}/follow")
    public List<Seller> getSellersFollowedByBuyer(@PathVariable String userName) {
        return buyerService.getBuyerByUsername(userName).getSellersFollowed();
    }

    @PostMapping("/{buyerUserName}/follow/{sellerUserName}")
    public void followSeller(@PathVariable String buyerUserName, @PathVariable String sellerUserName) {
        buyerService.followSeller(buyerService.getBuyerByUsername(buyerUserName).getBId(), sellerService.getSellerByUserName(sellerUserName).getId());
    }

    @PostMapping("/{buyerUserName}/unfollow/{sellerUserName}")
    public void unFollowSeller(@PathVariable String buyerUserName, @PathVariable String sellerUserName) {
        buyerService.unFollowSeller(buyerService.getBuyerByUsername(buyerUserName).getBId(), sellerService.getSellerByUserName(sellerUserName).getId());
    }

    @GetMapping("/{id}")
    public Buyer getBuyerById(@PathVariable("id") long id) {
        return buyerService.getBuyerBybId(id);
    }

    @PostMapping("/{userName}/order/returned/{id}")
    public Order returnedOrder(@PathVariable("userName") String userName, @PathVariable("id") long orderId) {
        return buyerService.returnedOrder(userName, orderId);
    }

    @PostMapping("/{userName}/order/delivered/{id}")
    public Order deliveredOrder(@PathVariable("userName") String userName, @PathVariable("id") long oId) {
        return orderService.getDeliveredOrder(userName, oId);
    }

    @GetMapping("/{userName}/order/{id}")
    public Order getOrderByBuyerUserNameOrderId(@PathVariable long id, @PathVariable String userName) {
        return buyerService.getOrderByBuyerUserNameOrderId(id, userName);
    }

    @PostMapping("/{userName}/order")
    public void addOrder(@RequestBody OrderDTO orderDTO, @PathVariable String userName) {
        buyerService.addOrder(orderDTO, userName);
    }

    @DeleteMapping("/{userName}/order/{id}")
    public boolean deleteOrder(@PathVariable("userName") String userName, @PathVariable("id") long id) {
        return buyerService.deleteOrder(userName, id);
    }

    @GetMapping("/{userName}/orders")
    public List<Order> getAllOrdersForBuyer(@PathVariable String userName) {
        return buyerService.getAllOrdersForBuyer(buyerService.getBuyerByUsername(userName).getBId());
    }

    @GetMapping("/{userName}/order/{id}/listItems")
    public List<LineItem> listOrderItems(@PathVariable("userName") String userName, @PathVariable("id") long id) {
        return buyerService.listOrderItems(userName, id);
    }

    @PostMapping("/profile/{sellerUserName}/billing")
    public Address addBillingAddress(@PathVariable String sellerUserName, @RequestBody Address address) {
        return buyerService.addBillingAddress(sellerUserName, address);
    }

    @PutMapping("/profile/{seller_UserName}/billing/{addressId}")
    public Address editBillingAddress(@PathVariable String seller_UserName, @RequestBody Address address,
                                      @PathVariable long addressId) {
        return buyerService.editBillingAddress(addressId, address);
    }

    @PostMapping("/profile/{sellerUserName}/shipping")
    public Address addSippingAddress(@PathVariable String sellerUserName, @RequestBody Address address) {
        return buyerService.addShippingAddress(sellerUserName, address);
    }

    @GetMapping("/profile/{userName}")
    public Buyer getBuyerByUserName(@PathVariable String userName) {
        return buyerService.getBuyerByUsername(userName);
    }

    @PostMapping("/profile/{sellerUserName}/card")
    public CreditCard addCardAddress(@PathVariable String sellerUserName, @RequestBody CreditCard creditCard) {
        return buyerService.addCreditCard(sellerUserName, creditCard);
    }

    @PutMapping("/profile/{sellerUserName}/card/{cardId}")
    public CreditCard editCardAddress(@PathVariable String sellerUserName, @RequestBody CreditCard creditCard, @PathVariable long cardId) {
        return buyerService.editCreditCard(cardId, creditCard);
    }

    @PutMapping("/profile/{sellerUserName}/shipping/{addressId}")
    public Address editSippingAddress(@PathVariable String sellerUserName, @RequestBody Address address, @PathVariable long addressId) {
        return buyerService.editShippingAddress(addressId, address);
    }
}
