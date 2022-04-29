package com.waazon.backend.services.Impls;

import java.util.Optional;

import com.waazon.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.waazon.backend.domains.Admin;
import com.waazon.backend.domains.Buyer;
import com.waazon.backend.domains.Role;
import com.waazon.backend.domains.Seller;
import com.waazon.backend.domains.User;
import com.waazon.backend.dtos.UserRegisterDTO;
import com.waazon.backend.repositories.AdminRepo;
import com.waazon.backend.repositories.BuyerRepo;
import com.waazon.backend.repositories.SellerRepo;
import com.waazon.backend.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {
    AdminRepo adminRepo;
    SellerRepo sellerRepo;
    BuyerRepo buyerRepo;
    PasswordEncoder passwordEncoder;
    UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, SellerRepo sellerRepo, BuyerRepo buyerRepo, AdminRepo adminRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.sellerRepo = sellerRepo;
        this.buyerRepo = buyerRepo;
        this.adminRepo = adminRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findUserByUserName(String userName) {
        return userRepo.findUserByUsername(userName);

    }

    @Override
    public void addSeller(Seller seller) {
        sellerRepo.save(seller);
    }

    @Override
    public void addBuyer(Buyer buyer) {
        buyerRepo.save(buyer);
    }

    @Override
    public void addAdmin(Admin admin) {
        adminRepo.save(admin);
    }

    @Override
    public boolean createUser(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        Role role = new Role();
        User findUSer = userRepo.findUserByUsername(userRegisterDTO.getUsername()).orElse(null);
        if (findUSer != null) {
            return false;
        }

        role.setRole(userRegisterDTO.getRole());

        user.setEmail(userRegisterDTO.getEmail());
        user.setFName(userRegisterDTO.getFirstName());
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setLName(userRegisterDTO.getLastName());
        user.addRole(role);

        switch (userRegisterDTO.getRole()) {
            case "ADMIN":
                Admin admin = new Admin();
                admin.setUser(user);
                addAdmin(admin);
                break;

            case "SELLER":
                Seller seller = new Seller();
                seller.setUser(user);
                addSeller(seller);
                break;

            case "BUYER":
                Buyer buyer = new Buyer();
                buyer.setUser(user);
                addBuyer(buyer);
                break;
        }

        userRepo.save(user);
        return true;
    }

}
