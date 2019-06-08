package com.company.dao.implementation;

import com.company.dao.BusDao;
import com.company.entity.Bus;
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

        while (resultSet.next()){
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

        return new Bus(id,login,driverId,routeId);
    }

    @Override
    public List<Bus> findInLimit(int page) {
        Connection connection = connector.getConnection();
        try {
            //Try-with-resources
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM buses LIMIT ?, 5");

            preparedStatement.setInt(1,page*5-5);

            ResultSet resultSet = preparedStatement.executeQuery();



            return mapResultSetToList(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public Optional<Bus> findByDriverId(Long id) {
        Connection connection = connector.getConnection();
        Bus found = null;
        try {
            //Try-with-resources
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM "+ table +" WHERE driverId=? ");

            preparedStatement.setLong(1,id);


            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                found=mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return Optional.ofNullable(found);
    }

    @Override
    public void update(long id, long routeId, long driverId) {
        Connection connection = connector.getConnection();
        try {
            //Try-with-resource

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

    @Override
    public int getCount() {

        Connection connection = connector.getConnection();
        try {
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
