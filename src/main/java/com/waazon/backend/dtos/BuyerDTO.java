package com.waazon.backend.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.waazon.backend.domains.*;

@Getter
@Setter
public class BuyerDTO {
    User user;
    long points;
    Address shippingAddress;
    List<Order> orders;
    List<PaymentMethod> paymentMethods;
    List<Seller> sellersFollowed;
    Address billingAddress;
}
