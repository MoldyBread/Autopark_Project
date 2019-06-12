package com.autopark.dao;

import com.autopark.entity.users.User;

import java.util.Optional;

/**
 * Interface class for user(both admin and driver) data access object
 *
 * @author Liash Danylo
 */
public interface UserDao<T extends User> extends GenericDao<T> {
    /**
     * Finds user in db by login and password
     *
     * @param login input login
     * @param password input password
     * @return Optional user from db by login and password
     */
    Optional<T> findByLoginAndPassword(String login, String password);

    /**
     * Finds user by id
     *
     * @param id id of user to find
     * @return Optional of User by id
     */
    Optional<T> findById(Long id);
}
