package com.company.entity;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private Long id;
    private int mileage;
    private List<String> busPlates;

    public Route(Long id, int mileage) {
        this.id = id;
        this.mileage = mileage;
        this.busPlates = new ArrayList<>();
    }

    public void addBus(String plate) {
        busPlates.add(plate);
    }

    public Long getId() {
        return id;
    }

    public int getMileage() {
        return mileage;
    }

    public List<String> getBusPlates() {
        return busPlates;
    }
}
