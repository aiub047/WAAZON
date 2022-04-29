package com.waazon.backend.dtos;

import lombok.Getter;
import lombok.Setter;

import com.waazon.backend.domains.ProductCategory;
import com.waazon.backend.domains.Seller;

import java.time.LocalDate;

@Getter
@Setter
public class ProductDTO {
    private String title;
    private boolean featured=false;
    private double rating;
    private String description;
    private double price;
    private String status;
    private Seller seller;
    private ProductCategory productCategory;
    private LocalDate createdOn;


}
