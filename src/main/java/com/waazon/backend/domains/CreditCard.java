package com.waazon.backend.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Pattern(regexp = "\\d{4}-\\d{4}-\\d{4}-\\d{4}", message = "Invalid Card Number")
    String cardNumber;

    @Pattern(regexp = "\\d{3}", message = "Invalid CVV")
    String cvv;

    @Pattern(regexp = "\\d{2}-\\d{2}", message = "Invalid expiration date")
    String expDate;
}
