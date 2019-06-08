package com.company.dao;

import com.company.entity.users.Driver;

import java.sql.SQLException;
import java.util.List;

public interface DriverDao extends UserDao<Driver> {
    void update(long id, boolean accepted);
    List<Driver> findFree();
}
