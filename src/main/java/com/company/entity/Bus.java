package com.company.entity;

import com.company.entity.users.Driver;

public class Bus {
    private String plate;
    private Driver driver;

    public Bus(String plate, Driver driver) {
        this.plate = plate;
        this.driver = driver;
    }
}
