package com.company.service.impl;

import com.company.dao.AdminDao;
import com.company.entity.users.Admin;
import com.company.service.AdminService;

import java.util.Optional;

public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao;

    public AdminServiceImpl(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public Optional<Admin> findByLoginAndPassword(String login, String password) {
        return adminDao.findByLoginAndPassword(login,password);
    }
}
