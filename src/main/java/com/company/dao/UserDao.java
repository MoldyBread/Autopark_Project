package com.company.dao;

import com.company.entity.users.User;

import java.util.Optional;

public interface UserDao<T extends User> extends GenericDao<T> {
    Optional<T> findByLoginAndPassword(String login, String password);
}
