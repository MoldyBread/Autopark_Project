package com.autopark.service;

import com.autopark.entity.Bus;

import java.util.List;
import java.util.Optional;

/**
 *
 * Interface class for actions with BusDao
 *
 * @author Liash Danylo
 */
public interface BusService {
    List<Bus> findAll();

    Optional<Bus> findByDriverId(Long id);

    List<Bus> findInLimit(int page);

    int getCount();

    void update(long id, long routeId, long driverId);
}
