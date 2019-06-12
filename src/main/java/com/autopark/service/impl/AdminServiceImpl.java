package com.autopark.service.impl;

import com.autopark.dao.AdminDao;
import com.autopark.entity.users.Admin;
import com.autopark.service.AdminService;

import java.util.Optional;

/**
 *
 * Implementation of AdminService
 *
 * @author Liash Danylo
 */
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
