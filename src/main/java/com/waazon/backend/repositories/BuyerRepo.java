package com.waazon.backend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.waazon.backend.domains.Address;
import com.waazon.backend.domains.Buyer;
import com.waazon.backend.domains.Order;
import com.waazon.backend.domains.Seller;

import java.util.List;

@Repository
public interface BuyerRepo extends CrudRepository<Buyer, Long> {
    @Query("select b from Buyer b where b.bId = :bId")
    Buyer findBuyerByBId(@Param("bId") long id);

    @Query("select b from Buyer b where b.user.username = :userName")
    Buyer findBuyerByUsername(@Param("userName") String userName);

    @Query("select b from Buyer b where b.user.email = :email ")
    Buyer findBuyerByEmail(@Param("email") String email);

    @Query("select b.sellersFollowed from Buyer b where b.bId = :id ")
    List<Seller> getSellerFollowedByBuyerId(@Param("id") long id);

    @Query("select b from Buyer b")
    List<Buyer> findAllBuyers();

    @Query("select b.orders from Buyer b where b.bId = :id ")
    List<Order> getAllOrdersByBuyerId(@Param("id") long id);

    @Query("select b.shippingAddress from Buyer b where b.bId=:id")
    Address findShippingAddress(@Param("id") long id);

    @Query("select b.points from Buyer b where b.bId = :id ")
    long getBuyerPoints(@Param("id") long id);

    @Query(value = "select o.id from ord o inner join buyer_orders bo  on o.id=bo.orders_id where bo.buyer_b_id=:bId and o.id=:id", nativeQuery = true)
    long getOrderByBuyerUserNameAndOrderId(long id, long bId);
}
