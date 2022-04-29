package com.waazon.backend.domains;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bId;

    @Digits(integer = 6, fraction = 0)
    long points = 0;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable
    @Fetch(FetchMode.JOIN)
    private List<Order> orders;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "User_ID")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Address shippingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private Address billingAddress;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<Seller> sellersFollowed = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private CreditCard creditCard = new CreditCard();
}