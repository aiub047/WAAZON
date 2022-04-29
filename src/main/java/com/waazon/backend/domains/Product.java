package com.waazon.backend.domains;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private String image;
    private double rating = 0;

    public void setAvgRating() {
        if (null != reviews)
            this.rating = reviews.stream().map(Review::getStars).reduce(0.0, Double::sum) / reviews.size();
    }

    private String status = ProductStatus.PENDING.getProductStatus();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable
    @JsonIgnore
    private List<Review> reviews;

    @NotNull
    @Digits(fraction = 2, message = "Price Not Valid", integer = 5)
    private double price = 0;

    @DateTimeFormat
    private LocalDate createdOn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Seller seller;

    private Long numberInStock;

    @Override
    public String toString() {
        return "Product: \n" + "\n\ttitle : " + title + '\n' + "\n\tdescription : " + description + '\n';
    }
}
