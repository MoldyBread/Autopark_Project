package com.company.service.impl;

import com.company.dao.RouteDao;
import com.company.entity.Route;
import com.company.service.RouteService;

import java.util.List;

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
