package com.waazon.backend.domains;

public enum OrderStatus {
    SHIPPED("Shipped"),
    RETURNED("Returned"),
    ONTHEWAY("On the way"),
    CANCELLED("Cancelled"),
    DELIVERED("Delivered"),
    PENDING("Pending");

    private final String orderStatus;

    OrderStatus(String status) {
        this.orderStatus = status;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }
}
