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
    private final Logger logger = Logger.getLogger(BusDaoImpl.class);

    public BusDaoImpl(Connector connector) {
        super("buses", connector);
    }

    @Override
    public List<Bus> mapResultSetToList(ResultSet resultSet) throws SQLException {
        List<Bus> buses = new ArrayList<>();

        while (resultSet.next()){
            buses.add(mapResultSetToEntity(resultSet));
        }

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

            Optional<Bus> settedWithDriver = findByDriverId(driverId);

            if(settedWithDriver.isPresent()){
                Bus bus = settedWithDriver.get();
                PreparedStatement preparedStatement1 = connection
                        .prepareStatement("UPDATE buses SET driverId=? WHERE id=?");
                preparedStatement1.setLong(1, -1);
                preparedStatement1.setLong(2, bus.getId());

                preparedStatement1.executeUpdate();
            }

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
}
