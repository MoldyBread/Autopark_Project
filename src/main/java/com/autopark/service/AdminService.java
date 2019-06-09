package com.autopark.service;

import com.autopark.entity.users.Admin;

import java.util.Optional;

public interface AdminService {
    Optional<Admin> findByLoginAndPassword(String login, String password);
}
