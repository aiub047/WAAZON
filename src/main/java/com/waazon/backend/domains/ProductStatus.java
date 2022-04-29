package com.waazon.backend.domains;

public enum ProductStatus {
    APPROVED("Approved"),
    REJECTED("Rejected"),
    PENDING("Pending");

    private final String productStatus;

    ProductStatus(String status){this.productStatus=status;}

    public String getProductStatus(){
        return this.productStatus;
    }
}
