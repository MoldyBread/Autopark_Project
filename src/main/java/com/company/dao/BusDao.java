package com.company.dao;

import com.company.entity.Bus;

import java.util.List;
import java.util.Optional;

public interface BusDao extends GenericDao<Bus> {
    List<Bus> findInLimit(int page);
    Optional<Bus> findByDriverId(Long id);
    void update(long id, long routeId,long driverId);
    int getCount();
}
