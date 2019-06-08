package com.company.service.impl;

import com.company.dao.BusDao;
import com.company.entity.Bus;
import com.company.service.BusService;

import java.util.List;
import java.util.Optional;

public class BusServiceImpl implements BusService {

    private BusDao busDao;

    public BusServiceImpl(BusDao busDao) {
        this.busDao = busDao;
    }

    @Override
    public List<Bus> findAll() {
        return busDao.findAll();
    }

    @Override
    public Optional<Bus> findByDriverId(Long id) {
        return busDao.findByDriverId(id);
    }

    @Override
    public List<Bus> findInLimit(int page) {
        return busDao.findInLimit(page);
    }

    @Override
    public int getCount() {
        return busDao.getCount();
    }

    @Override
    public void update(long id, long routeId, long driverId) {
        busDao.update(id, routeId, driverId);
    }
}