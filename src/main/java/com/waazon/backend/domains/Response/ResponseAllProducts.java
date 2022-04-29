package com.waazon.backend.domains.Response;

import com.waazon.backend.domains.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseAllProducts extends Response {
    private List<Product> products;
}
