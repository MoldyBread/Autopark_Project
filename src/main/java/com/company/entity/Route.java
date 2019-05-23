package com.company.entity;

import java.util.List;

public class Route {
    private Long id;
    private List<Bus> buses;

    public Route(Long id, List<Bus> buses) {
        this.id = id;
        this.buses = buses;
    }
}
