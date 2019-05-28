package com.company.dao.implementation;

import com.company.dao.UserDao;
import com.company.entity.users.Driver;
import com.company.entity.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public abstract class UserDaoImpl<T extends User> extends GenericDaoImpl<T>  implements UserDao<T>{



    protected UserDaoImpl(String table, Connector connector) {
        super(table, connector);
    }

    @Override
    public Optional<T> findByLoginAndPassword(String login, String password) {
        Connection connection = connector.getConnection();
        T found = null;
        try {
            //Try-with-resources
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM "+ table +" WHERE login=? AND psswrd=?");

            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);



            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                found=mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            //TODO: LOGGER
            e.printStackTrace();
        }

        return Optional.ofNullable(found);
    }
}
