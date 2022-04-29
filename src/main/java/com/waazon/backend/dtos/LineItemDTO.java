package com.waazon.backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineItemDTO {
    private long productId;
    private int quantity;
    private double price;
}
