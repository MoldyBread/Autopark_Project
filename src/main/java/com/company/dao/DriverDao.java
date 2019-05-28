package com.company.dao;

import com.company.entity.users.Driver;

import java.sql.SQLException;

public interface DriverDao extends UserDao<Driver> {
    void update(long id, boolean accepted) throws SQLException;
}
