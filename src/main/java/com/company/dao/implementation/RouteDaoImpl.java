package com.company.dao.implementation;

import com.company.dao.RouteDao;
import com.company.entity.Route;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl extends GenericDaoImpl<Route> implements RouteDao {
    public RouteDaoImpl(Connector connector) {
        super("routes", connector);
    }

    @Override
    public List<Route> mapResultSetToList(ResultSet resultSet) throws SQLException {
        List<Route> routes = new ArrayList<>();
        while (resultSet.next()){
            Route route = mapResultSetToEntity(resultSet);
            if(route.getId()!=-1)
            routes.add(route);
        }
        return routes;
    }

    @Override
    public Route mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        int mileage = resultSet.getInt("mileage");

        return new Route(id,mileage);
    }
}
