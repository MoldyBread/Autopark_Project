package com.company.service;

import com.company.entity.users.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    Optional<Driver> findById(Long id);

    Optional<Driver> findByLoginAndPassword(String login, String password);

    List<Driver> findInLimit(int page);

    List<Driver> findFree();

    int getCount();

    void update(long id, boolean accepted);
}
