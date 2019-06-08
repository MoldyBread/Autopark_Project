package com.company.service;

import com.company.entity.users.Admin;

import java.util.Optional;

public interface AdminService {
    Optional<Admin> findByLoginAndPassword(String login, String password);
}
