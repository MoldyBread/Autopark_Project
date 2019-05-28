package com.company.entity;

import java.util.List;

public class Route {
    private Long id;
    private int mileage;
    private List<Bus> buses;

    public Route(Long id, int mileage, List<Bus> buses) {
        this.id = id;
        this.mileage = mileage;
        this.buses = buses;
    }
}
