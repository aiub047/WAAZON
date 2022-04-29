package com.waazon.backend.services.Impls;


import java.util.List;
import java.util.Objects;

import com.waazon.backend.domains.Order;
import com.waazon.backend.domains.OrderStatus;
import com.waazon.backend.domains.ProductStatus;
import com.waazon.backend.domains.Seller;
import com.waazon.backend.repositories.OrderRepo;
import com.waazon.backend.repositories.SellerRepo;
import com.waazon.backend.services.OrderService;
import com.waazon.backend.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@SuppressWarnings("unused")
public class SellerServiceImpl implements SellerService {
    SellerRepo sellerRepo;
    OrderRepo orderRepo;
    OrderService orderService;

    @Autowired
    public SellerServiceImpl(SellerRepo sellerRepo, OrderRepo orderRepo, OrderService orderService) {
        this.sellerRepo = sellerRepo;
        this.orderRepo = orderRepo;
        this.orderService = orderService;
    }

    public Seller getSellerByEmail(String email) {
        return sellerRepo.findSellerByEmail(email);
    }

    @Override
    public Seller getSellerByUserName(String userName) {
        return sellerRepo.findSellerBySUserName(userName);
    }

    @Override
    public Seller getSellerById(long id) {
        return sellerRepo.findSellerById(id);
    }

    @Override
    public List<Order> getOrdersBySellerId(long sellerId) {
        return sellerRepo.getOrdersBySellerBySId(sellerId);
    }

    @Override
    public Seller approveOrReject(Long id, String status) {
        try {
            Seller seller = sellerRepo.findSellerById(id);
            if (seller != null) {
                if ("Approved".equals(status)) {
                    seller.setStatus(ProductStatus.APPROVED.getProductStatus());
                    return sellerRepo.save(seller);
                }
                if (status.equals("Rejected")) {
                    seller.setStatus(ProductStatus.REJECTED.getProductStatus());
                    return sellerRepo.save(seller);
                }
                return null;
            }
            return null;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Order> getOrderIdsBySellerId(long sId) {
        return sellerRepo.getOrdersBySellerBySId(sId);
    }

    @Override
    public List<Seller> getAllSellers() {
        return (List<Seller>) sellerRepo.findAll();
    }

    @Override
    public void addSeller(Seller seller) {
        sellerRepo.save(seller);
    }

    @Override
    public void save(Seller seller) {
        sellerRepo.save(seller);
    }

    @Override
    public Order onTheWaySellerOrder(String userName, long oId) {
        Seller seller = getSellerByUserName(userName);

        List<Order> orders = this.getOrderIdsBySellerId(seller.getId());

        Order order = orders.stream().filter(o -> o.getId() == oId).findFirst().orElse(null);

        assert order != null;
        if (Objects.equals(order.getOrderStatus(), OrderStatus.SHIPPED.getOrderStatus()))
            order.setOrderStatus(OrderStatus.ONTHEWAY.getOrderStatus());
        orderService.save(order);

        return order;
    }

    @Override
    public Order shipSellerOrder(String userName, long orderId) {
        Seller seller = sellerRepo.findSellerByUsername(userName);
        List<Order> orders = this.getOrderIdsBySellerId(seller.getId());

        Order order = orders.stream().filter(o -> o.getId() == orderId).findFirst().orElse(null);

        assert order != null;
        order.setOrderStatus(OrderStatus.SHIPPED.getOrderStatus());
        orderService.save(order);
        return order;
    }

    @Override
    public Order deliveredSellerOrder(String userName, long orderId) {
        Seller seller = getSellerByUserName(userName);

        List<Order> orders = this.getOrderIdsBySellerId(seller.getId());

        Order order = orders.stream().filter(o -> o.getId() == orderId).findFirst().orElse(null);

        assert order != null;
        if (Objects.equals(order.getOrderStatus(), OrderStatus.ONTHEWAY.getOrderStatus()))
            order.setOrderStatus(OrderStatus.DELIVERED.getOrderStatus());
        orderService.save(order);

        return order;
    }

    @Override
    public Order cancelSellerOrder(String userName, long orderId) {
        Seller seller = getSellerByUserName(userName);

        List<Order> orders = this.getOrderIdsBySellerId(seller.getId());

        Order order = orders.stream().filter(o -> o.getId() == orderId).findFirst().orElse(null);

        assert order != null;
        if (Objects.equals(order.getOrderStatus(), OrderStatus.PENDING.getOrderStatus()))
            order.setOrderStatus(OrderStatus.CANCELLED.getOrderStatus());
        orderService.save(order);

        return order;
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    @Override
    public Order getOrderByIdForSeller(String userName, long orderId) {
        if (getOrderIdsBySellerId(getSellerByUserName(userName).getId()).contains(orderId)) {
            return orderService.getOrderById(orderId);
        }
        return null;
    }

}
