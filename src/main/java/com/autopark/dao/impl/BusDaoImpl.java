package com.autopark.dao.impl;

import com.autopark.dao.BusDao;
import com.autopark.entity.Bus;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BusDaoImpl extends GenericDaoImpl<Bus> implements BusDao {

    private static final Logger logger = Logger.getLogger(BusDaoImpl.class);

    public BusDaoImpl(Connector connector) {
        super("buses", connector);
    }

    @Override
    public List<Bus> mapResultSetToList(ResultSet resultSet) throws SQLException {
        List<Bus> buses = new ArrayList<>();

        while (resultSet.next()) {
            buses.add(mapResultSetToEntity(resultSet));
        }

        logger.info("Buses returned");
        return buses;
    }

    @Override
    public Bus mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String login = resultSet.getString("plate");
        long driverId = resultSet.getLong("driverId");
        long routeId = resultSet.getLong("routeId");

        return new Bus(id, login, driverId, routeId);
    }

    @Override
    public List<Bus> findInLimit(int page) {
        try (Connection connection = connector.getConnection()) {
            //Try-with-resources
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM buses LIMIT ?, 5");

            preparedStatement.setInt(1, page * 5 - 5);

            ResultSet resultSet = preparedStatement.executeQuery();


            return mapResultSetToList(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public Optional<Bus> findByDriverId(Long id) {
        Bus found = null;
        try (Connection connection = connector.getConnection()) {

            //Try-with-resources
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM " + table + " WHERE driverId=? ");

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
    public void update(long id, long routeId, long driverId) {
        try (Connection connection = connector.getConnection()) {
            //Try-with-resource
            resetLast(driverId, connection);

            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE buses SET driverId=?,routeId=? WHERE id=?");

            preparedStatement.setLong(1, driverId);
            preparedStatement.setLong(2, routeId);
            preparedStatement.setLong(3, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    private void resetLast(long driverId, Connection connection) throws SQLException {
        Optional<Bus> settedWithDriver = findByDriverId(driverId);

        if(settedWithDriver.isPresent()){
            Bus bus = settedWithDriver.get();
            PreparedStatement preparedStatement1 = connection
                    .prepareStatement("UPDATE buses SET driverId=? WHERE id=?");
            preparedStatement1.setLong(1, -1);
            preparedStatement1.setLong(2, bus.getId());

            preparedStatement1.executeUpdate();
        }
    }

    @Override
    public int getCount() {

        try (Connection connection = connector.getConnection()) {
            //Try-with-resource

            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT COUNT(*) FROM buses");


            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return 0;
    }
}