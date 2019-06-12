package com.autopark.dao;

import com.autopark.entity.users.Driver;

import java.util.List;

/**
 * Interface class for driver data access object
 *
 * @author Liash Danylo
 */
public interface DriverDao extends UserDao<Driver> {
    /**
     * Updates driver with workplace acception
     *
     * @param id id of driver to update
     * @param accepted value of acception of driver workplace
     */
    void update(long id, boolean accepted);

    /**
     * Finds free drivers
     *
     * @return List of free drivers
     */
    List<Driver> findFree();

    /**
     * Finds driers in limit
     *
     * @param page page in view to count limit
     * @return List of drivers in limit
     */
    List<Driver> findInLimit(int page);

    /**
     * Gets count of drivers in db
     *
     * @return count of drivers in db
     */
    int getCount();
}
