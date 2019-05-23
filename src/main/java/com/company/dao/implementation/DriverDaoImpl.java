package com.company.dao.implementation;

import com.company.dao.DriverDao;
import com.company.entity.users.Admin;
import com.company.entity.users.Driver;
import com.company.entity.users.User;
import com.company.entity.users.UserType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DriverDaoImpl extends UserDaoImpl implements DriverDao {
    public DriverDaoImpl(Connector connector) {
        super("drivers", connector);
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
        String name=resultSet.getString("firstname");
        String surname=resultSet.getString("lastname");
        boolean accepted = resultSet.getBoolean("accepted");

        return Driver.builder()
                .withAccepted(accepted)
                .withSurname(surname)
                .withName(name)
                .withId(id)
                .withLogin(login)
                .withPassword(password)
                .withUserType(UserType.DRIVER)
                .build();
    }


    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        Connection connection = connector.getConnection();
        User finded = null;
        try {
            //Try-with-resources
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM drivers WHERE login=? AND psswrd=?");

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
