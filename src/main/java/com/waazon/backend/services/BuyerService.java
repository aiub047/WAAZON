package com.waazon.backend.services;

import org.springframework.data.domain.Pageable;

import com.waazon.backend.domains.*;
import com.waazon.backend.dtos.OrderDTO;

import java.util.List;

public interface BuyerService {
    List<Order> getAllOrdersForBuyer(long id);

    Buyer getBuyerByUsername(String userName);

    void followSeller(long bId, long sId);

    void unFollowSeller(long bId, long sId);

    Address getShippingAddressBysId(long id);

    Buyer getBuyerBybId(long id);

    Buyer getBuyerByEmail(String email);

    List<Buyer> getAllBuyers();

    List<Buyer> getAllBuyerWithPagingAndSorting(Pageable pageable);

    List<Order> getAllOrderByBuyerId(long id);

    void addBuyer(Buyer buyer);

    Order getOrderByBuyerUserNameOrderId(long id, String userName);


    void save(Buyer buyer);

    Order returnedOrder(String userName, long oId);

    void addOrder(OrderDTO orderDTO, String userName);

    boolean deleteOrder(String userName, long id);

    List<LineItem> listOrderItems(String userName, long id);

    void editBuyer(String userName, Buyer buyer);

    Address addBillingAddress(String userName, Address address);

    Address editBillingAddress(long address_id, Address address);

    Address addShippingAddress(String userName, Address address);

    Address editShippingAddress(long address_id, Address address);

    CreditCard addCreditCard(String userName, CreditCard creditCard);

    CreditCard editCreditCard(long creditCard_id, CreditCard creditCard);

}
