package com.waazon.backend.services.Impls;

import java.util.List;
import java.util.stream.Collectors;

import com.waazon.backend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waazon.backend.domains.Buyer;
import com.waazon.backend.domains.LineItem;
import com.waazon.backend.domains.Order;
import com.waazon.backend.domains.OrderStatus;
import com.waazon.backend.repositories.BuyerRepo;
import com.waazon.backend.repositories.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
    OrderRepo orderRepo;
	@Autowired
	BuyerRepo buyerRepo;

	@Override
	public List<LineItem> getOrderLineItemsByOrderId(long id) {
		return orderRepo.findAllLineItemsByOrderId(id);
	}

	@Override
	public Order getOrderById(long id) {
		return orderRepo.findOrderById(id);
	}

	@Override
	public void save(Order order) {
		orderRepo.save(order);
	}

	@Override
	public Order getDeliveredOrder(String userName, long oId) {

		Buyer buyer = buyerRepo.findBuyerByUsername(userName);
		List<Long> lOID = buyer.getOrders().stream().map(o -> o.getId()).collect(Collectors.toList());
		if (lOID.contains(oId)) {
			Order order = orderRepo.findOrderById(oId);
			order.setOrderStatus(OrderStatus.DELIVERED.getOrderStatus());
			orderRepo.save(order);
			buyerRepo.save(buyer);
			return order;
		}
		return null;
	}

	public List<Order> findOrdersByBuyerId(long id) {
		return orderRepo.findAllOrdersByBuyerId(id);
	}
}
