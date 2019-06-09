package com.autopark.dao;

import com.autopark.entity.users.Driver;

import java.util.List;

public interface DriverDao extends UserDao<Driver> {
    void update(long id, boolean accepted);

    List<Driver> findFree();

    List<Driver> findInLimit(int page);

    int getCount();
}
