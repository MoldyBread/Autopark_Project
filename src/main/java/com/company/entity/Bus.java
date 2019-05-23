package com.company.entity;

import com.company.entity.users.Driver;

public class Bus {
    private Long id;
    private String plate;
    private Driver driver;

    public Bus(Long id, String plate, Driver driver) {
        this.id = id;
        this.plate = plate;
        this.driver = driver;
    }
}
