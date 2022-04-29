package com.waazon.backend.domains;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "order")
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    private LocalDate createdOn = LocalDate.now();
    private double price;

    @Valid
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable
    List<LineItem> lineItems = new ArrayList<>();
    Boolean orderPaid = false;
    String orderStatus = OrderStatus.PENDING.getOrderStatus();

    @ManyToOne
    @JsonIgnore
    private Buyer buyer;

    @Override
    public String toString() {
        return "Order : " + "\n\tid : " + id + "\n\tcreated on : " + createdOn + "\n\tprice : " + price + "\n\t" + lineItems + "\n\torder status : '" + orderStatus;
    }
}
