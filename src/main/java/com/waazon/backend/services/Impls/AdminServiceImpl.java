package com.waazon.backend.services.Impls;

import com.waazon.backend.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waazon.backend.domains.Admin;
import com.waazon.backend.repositories.AdminRepo;

@Service
public class AdminServiceImpl implements AdminService {
    AdminRepo adminRepo;

    @Autowired
    public AdminServiceImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Override
    public Admin getAdminProfile(String admin_userName) {
        return adminRepo.findAdminByUserName(admin_userName);
    }
}
