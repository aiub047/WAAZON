package com.waazon.backend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private double price;
    List<LineItemDTO> lineItemsDTO;
    private Date createdOn;
}
