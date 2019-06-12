package com.autopark.dao.impl;

import com.autopark.dao.AdminDao;
import com.autopark.entity.users.Admin;
import com.autopark.entity.users.UserType;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl extends UserDaoImpl<Admin> implements AdminDao {

    private static final Logger logger = Logger.getLogger(AdminDaoImpl.class);

    public AdminDaoImpl(Connector connector) {
        super("admins", connector);
    }

    @Override
    public List<Admin> mapResultSetToList(ResultSet resultSet) {
        logger.error("Trying to use unsupported operation");
        throw new UnsupportedOperationException("Unused void");
    }

    @Override
    public Admin mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String login = resultSet.getString("login");
        String password = resultSet.getString("psswrd");


        return Admin.builder()
                .withId(id)
                .withLogin(login)
                .withPassword(password)
                .withUserType(UserType.ADMIN)
                .build();
    }

}