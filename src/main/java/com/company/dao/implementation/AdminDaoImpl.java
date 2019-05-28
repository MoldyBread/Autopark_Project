package com.company.dao.implementation;

import com.company.dao.AdminDao;
import com.company.entity.users.Admin;
import com.company.entity.users.UserType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AdminDaoImpl extends UserDaoImpl<Admin> implements AdminDao {

    public static final String TABLE = "admins";

    public AdminDaoImpl(Connector connector) {
        super(TABLE, connector);
    }

    @Override
    public List<Admin> mapResultSetToList(ResultSet resultSet) {
        throw new UnsupportedOperationException("Hasn't implemented yet");
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
