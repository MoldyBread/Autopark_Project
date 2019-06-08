package com.company.service.impl;

import com.company.dao.DriverDao;
import com.company.entity.users.Driver;
import com.company.service.DriverService;

import java.util.List;
import java.util.Optional;

public class DriverServiceImpl implements DriverService {

    private DriverDao driverDao;

    public DriverServiceImpl(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    @Override
    public Optional<Driver> findById(Long id) {
        return driverDao.findById(id);
    }

    @Override
    public Optional<Driver> findByLoginAndPassword(String login, String password) {
        return driverDao.findByLoginAndPassword(login,password);
    }

    @Override
    public List<Driver> findInLimit(int page) {
        return driverDao.findInLimit(page);
    }

    @Override
    public List<Driver> findFree() {
        return driverDao.findFree();
    }

    @Override
    public int getCount() {
        return driverDao.getCount();
    }

    @Override
    public void update(long id, boolean accepted) {
        driverDao.update(id,accepted);
    }
}
