package com.company.dao.implementation;

import com.company.dao.DriverDao;
import com.company.entity.users.Driver;
import com.company.entity.users.UserType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DriverDaoImpl extends UserDaoImpl<Driver> implements DriverDao {

    public static final String TABLE = "drivers";

    public DriverDaoImpl(Connector connector) {
        super(TABLE, connector);
    }

    @Override
    public List<Driver> mapResultSetToList(ResultSet resultSet) {
        throw new UnsupportedOperationException("Hasn't implemented yet");
    }

    @Override
    public Driver mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String login = resultSet.getString("login");
        String password = resultSet.getString("psswrd");
        String name = resultSet.getString("firstname");
        String surname = resultSet.getString("lastname");
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
    public void update(long id, boolean accepted) throws SQLException {
        Connection connection = connector.getConnection();
        try {
            //Try-with-resources
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE drivers SET accepted=? WHERE id=?");

            preparedStatement.setBoolean(1, accepted);
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            //TODO: LOGGER
            e.printStackTrace();
        }


    }
}
