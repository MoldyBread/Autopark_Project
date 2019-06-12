package com.autopark.service.impl;

import com.autopark.dao.RouteDao;
import com.autopark.entity.Route;
import com.autopark.service.RouteService;

import java.util.List;

/**
 *
 * Implementation of RouteService
 *
 * @author Liash Danylo
 */
public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao;

    public RouteServiceImpl(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    @Override
    public List<Route> findAll() {
        return routeDao.findAll();
    }
}
