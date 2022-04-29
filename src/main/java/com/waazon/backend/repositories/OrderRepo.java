package com.waazon.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.waazon.backend.domains.LineItem;
import com.waazon.backend.domains.Order;

@Repository
public interface OrderRepo extends PagingAndSortingRepository<Order, Long> {
    @Query(value = "select o.lineItems from order o where o.id = :orderId")
    List<LineItem> findAllLineItemsByOrderId(long orderId);

    @Query(value = "select o from order o where o.buyer.id = :buyerId")
    List<Order> findAllOrdersByBuyerId(long buyerId);

    Order findOrderById(long id);
}
