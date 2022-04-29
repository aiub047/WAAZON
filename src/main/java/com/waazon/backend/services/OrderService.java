package com.waazon.backend.services;

import java.util.List;

import com.waazon.backend.domains.LineItem;
import com.waazon.backend.domains.Order;

@SuppressWarnings("unused")
public interface OrderService {
    void save(Order order);

    Order getOrderById(long id);

    List<LineItem> getOrderLineItemsByOrderId(long id);

    Order getDeliveredOrder(String userName, long oId);

    List<Order> findOrdersByBuyerId(long id);
}
