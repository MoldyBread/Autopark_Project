package com.autopark.dao.impl;

import com.autopark.dao.BusDao;
import com.autopark.dao.DriverDao;
import com.autopark.entity.Bus;
import com.autopark.entity.users.Driver;
import com.autopark.entity.users.UserType;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DriverDaoImpl extends UserDaoImpl<Driver> implements DriverDao {

    private static final Logger logger = Logger.getLogger(DriverDaoImpl.class);

    public DriverDaoImpl(Connector connector) {
        super("drivers", connector);
    }

    @Override
    public List<Driver> mapResultSetToList(ResultSet resultSet) throws SQLException {
        List<Driver> drivers = new ArrayList<>();


        while (resultSet.next()) {
            Driver driver = mapResultSetToEntity(resultSet);
            if (driver.getId() != -1L) {
                drivers.add(driver);
            }
        }

        logger.info("Drivers returned");
        return drivers;
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
    public void update(long id, boolean accepted) {
        try (Connection connection = connector.getConnection()) {
            //Try-with-resources

            if (!accepted) {
                BusDao busDao = new BusDaoImpl(connector);
                Optional<Bus> foundBus = busDao.findByDriverId(id);

                if (foundBus.isPresent()) {
                    Bus bus = foundBus.get();
                    busDao.update(bus.getId(), bus.getRouteId(), -1);

                }
            }
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE drivers SET accepted=? WHERE id=?");

            preparedStatement.setBoolean(1, accepted);
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }


    @Override
    public List<Driver> findFree() {

        try (Connection connection = connector.getConnection()) {
            //Try-with-resources
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM drivers WHERE accepted=0");

            ResultSet resultSet = preparedStatement.executeQuery();

            return mapResultSetToList(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return new ArrayList<>();

    }

    @Override
    public List<Driver> findInLimit(int page) {
        try (Connection connection = connector.getConnection()) {
            //Try-with-resources
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM drivers LIMIT ?, 5");

            preparedStatement.setInt(1, page * 5 - 4);

            ResultSet resultSet = preparedStatement.executeQuery();

            return mapResultSetToList(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public int getCount() {
        try (Connection connection = connector.getConnection()) {
            //Try-with-resource

            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT COUNT(*) FROM drivers");


            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) - 1;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return 0;
    }
}
