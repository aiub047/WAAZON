package com.waazon.backend.domains.Response;

import com.waazon.backend.domains.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseOneProduct extends Response {
    private Product product;
}
