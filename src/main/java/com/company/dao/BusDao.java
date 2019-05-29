package com.company.dao;

import com.company.entity.Bus;

import java.util.Optional;

public interface BusDao extends GenericDao<Bus> {
    Optional<Bus> findByDriverId(Long id);
}
