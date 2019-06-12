package com.autopark.dao;

import com.autopark.entity.Bus;

import java.util.List;
import java.util.Optional;

/**
 *
 * Interface class for bus data access object
 *
 * @author Liash Danylo
 */
public interface BusDao extends GenericDao<Bus> {
    /**
     * Finds buses in limit
     *
     * @param page page in view to show limit
     * @return list of buses in limit
     */
    List<Bus> findInLimit(int page);

    /**
     * Finds bus by driver id
     *
     * @param id id of driver to find
     * @return Optional bus by driver id
     */
    Optional<Bus> findByDriverId(Long id);

    /**
     * updates bus
     *
     * @param id id of bus to update
     * @param routeId routeid change
     * @param driverId driverod change
     */
    void update(long id, long routeId, long driverId);

    /**
     * Gets count of buses in db
     *
     * @return count of bus rows in db
     */
    int getCount();
}
