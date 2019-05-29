package com.company.dao.implementation;

import com.company.dao.BusDao;
import com.company.entity.Bus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BusDaoImpl extends GenericDaoImpl<Bus> implements BusDao {

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
            //logger
            e.printStackTrace();
        }

        return Optional.ofNullable(found);
    }
}
