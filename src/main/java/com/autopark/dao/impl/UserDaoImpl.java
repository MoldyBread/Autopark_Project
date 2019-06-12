package com.autopark.dao.impl;

import com.autopark.dao.UserDao;
import com.autopark.entity.users.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * Implementation of UserDao
 *
 * @author Liash Danylo
 */
public abstract class UserDaoImpl<T extends User> extends GenericDaoImpl<T> implements UserDao<T> {
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    protected UserDaoImpl(String table, Connector connector) {
        super(table, connector);
    }

    @Override
    public Optional<T> findById(Long id) {
        T found = null;

        try (Connection connection = connector.getConnection()) {

            //Try-with-resources
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM " + table + " WHERE id=?");

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                found = mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return Optional.ofNullable(found);
    }

    @Override
    public Optional<T> findByLoginAndPassword(String login, String password) {
        T found = null;

        try (Connection connection = connector.getConnection()) {
            //Try-with-resources
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM " + table + " WHERE login=? AND psswrd=?");

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                found = mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return Optional.ofNullable(found);
    }
}
