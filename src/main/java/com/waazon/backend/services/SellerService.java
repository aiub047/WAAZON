package com.waazon.backend.services;

import java.util.List;

import com.waazon.backend.domains.Order;
import com.waazon.backend.domains.Seller;

@SuppressWarnings("unused")
public interface SellerService {
    void addSeller(Seller seller);

    void save(Seller seller);

    List<Seller> getAllSellers();

    Seller getSellerById(long id);

    Seller getSellerByUserName(String userName);

    Order shipSellerOrder(String userName, long oId);

    List<Order> getOrderIdsBySellerId(long id);

    Order getOrderByIdForSeller(String userName, long oId);

    List<Order> getOrdersBySellerId(long id);

    Seller approveOrReject(Long id, String status);

    Order deliveredSellerOrder(String userName, long oId);

    Order cancelSellerOrder(String userName, long oId);

    Order onTheWaySellerOrder(String userName, long oId);
}
