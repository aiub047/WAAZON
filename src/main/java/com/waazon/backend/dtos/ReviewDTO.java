package com.waazon.backend.dtos;

import com.waazon.backend.domains.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewDTO {
    private String status;
    private String comment;
    private Product product;
    private double stars;
}
