package com.autopark.dao;

import com.autopark.entity.users.User;

import java.util.Optional;

public interface UserDao<T extends User> extends GenericDao<T> {
    Optional<T> findByLoginAndPassword(String login, String password);

    Optional<T> findById(Long id);
}
