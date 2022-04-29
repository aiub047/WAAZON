package com.waazon.backend.services;

import com.waazon.backend.domains.Admin;

public interface AdminService {
    Admin getAdminProfile(String admin_userName);
}
