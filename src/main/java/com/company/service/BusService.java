package com.company.service;

import com.company.entity.Bus;

import java.util.List;
import java.util.Optional;

public interface BusService {
    List<Bus> findAll();

    Optional<Bus> findByDriverId(Long id);

    List<Bus> findInLimit(int page);

    int getCount();

    void update(long id, long routeId, long driverId);
}
