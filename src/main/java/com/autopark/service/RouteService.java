package com.autopark.service;

import com.autopark.entity.Route;

import java.util.List;

/**
 *
 * Interface class for actions with RoutesDao
 *
 * @author Liash Danylo
 */
public interface RouteService {
    List<Route> findAll();
}
