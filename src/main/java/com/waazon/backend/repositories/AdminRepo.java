package com.waazon.backend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.waazon.backend.domains.Admin;

public interface AdminRepo extends CrudRepository<Admin,Long> {

    @Query(value = "select admin from Admin admin where admin.user.username=:admin_userName")
    Admin findAdminByUserName(String admin_userName);
}
