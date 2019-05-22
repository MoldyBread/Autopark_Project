package com.company.dao.implementation;

import com.company.dao.AdminDao;
import com.company.entity.users.Admin;
import com.company.entity.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AdminDaoImpl extends UserDaoImpl implements AdminDao {

    public AdminDaoImpl(String table, Connector connector) {
        super(table, connector);
    }

    @Override
    public List<User> mapResultSetToList(ResultSet resultSet) {
        throw new UnsupportedOperationException("Hasn't implemented yet");
    }

    @Override
    public User mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String login = resultSet.getString("login");
        String password = resultSet.getString("psswrd");

        return new Admin(id,login,password);
    }


    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        Connection connection = connector.getConnection();
        User finded = null;
        try {
            //Try-with-resources
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM admins WHERE login=? AND psswrd=?");

            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);



            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                finded=mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            //logger
            e.printStackTrace();
        }

        return Optional.ofNullable(finded);
    }
}
