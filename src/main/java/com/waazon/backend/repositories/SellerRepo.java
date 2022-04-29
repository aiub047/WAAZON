package com.waazon.backend.repositories;

import com.waazon.backend.domains.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.waazon.backend.domains.Product;
import com.waazon.backend.domains.Seller;

import java.util.List;

@Repository
public interface SellerRepo extends CrudRepository<Seller, Long> {

    @Query("select s from Seller s where s.user.username=:userName")
    Seller findSellerByUsername(String userName);

    @Query("select s from Seller s where s.user.email=:email")
    Seller findSellerByEmail(@Param("email") String email);

    Seller findSellerById(@Param("id") long id);

    @Query(value = "SELECT DISTINCT ord FROM order ord INNER JOIN FETCH ord.lineItems li where li.product.seller.id = :sId")
    List<Order> getOrdersBySellerBySId(@Param("sId") long sId);

    @Query("select s from Seller s where s.user.username=:userName")
    Seller findSellerBySUserName(String userName);

    @Query(value = "select p from Product p INNER JOIN p.seller s where s.user.username=:sellerUserName")
    List<Product> findAllProductBySellerUserName(String sellerUserName, Pageable pageable);
}
