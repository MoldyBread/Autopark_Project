package com.autopark.dao;

import java.util.List;

/**
 * Interface class to implement for entity data access objects
 *
 * @author Liash Danylo
 */
public interface GenericDao<T> {
    /**
     * Finds all entities in db
     * @return List of all entities in db
     */
    List<T> findAll();
}
