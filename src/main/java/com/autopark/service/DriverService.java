package com.autopark.service;

import com.autopark.entity.users.Driver;

import java.util.List;
import java.util.Optional;

/**
 *
 * Interface class for actions with DriverDao
 *
 * @author Liash Danylo
 */
public interface DriverService {
    Optional<Driver> findById(Long id);

    Optional<Driver> findByLoginAndPassword(String login, String password);

    List<Driver> findInLimit(int page);

    List<Driver> findFree();

    int getCount();

    void update(long id, boolean accepted);
}
