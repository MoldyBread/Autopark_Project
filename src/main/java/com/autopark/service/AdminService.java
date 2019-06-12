package com.autopark.service;

import com.autopark.entity.users.Admin;

import java.util.Optional;

/**
 *
 * Interface class for actions with AdminDao
 *
 * @author Liash Danylo
 */
public interface AdminService {
    Optional<Admin> findByLoginAndPassword(String login, String password);
}
