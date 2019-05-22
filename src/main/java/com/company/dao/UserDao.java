package com.company.dao;

import com.company.entity.users.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByLoginAndPassword(String login, String password);
}
