package com.waazon.backend.services;

import org.springframework.stereotype.Service;

import com.waazon.backend.domains.Admin;
import com.waazon.backend.domains.Buyer;
import com.waazon.backend.domains.Seller;
import com.waazon.backend.domains.User;
import com.waazon.backend.dtos.UserRegisterDTO;

import java.util.Optional;

@Service
@SuppressWarnings("unused")
public interface UserService {
    Optional<User> findUserByUserName(String userName);

    boolean createUser(UserRegisterDTO userRegisterDTO);

    void addSeller(Seller seller);

    void addBuyer(Buyer buyer);

    void addAdmin(Admin admin);

}
