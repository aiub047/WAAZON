package com.waazon.backend.domains;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Digits(integer = 1, fraction = 2, message = "Invalid stars")
    private double stars;

    @NotBlank
    private String comment;

    @ManyToOne
    private Product product;

    private String status = ProductStatus.PENDING.getProductStatus();
}
