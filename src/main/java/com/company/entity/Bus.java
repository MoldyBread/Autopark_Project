package com.company.entity;


public class Bus {
    private final Long id;
    private final String plate;
    private Long driverId;
    private Long routeId;

    public Bus(Long id, String plate, Long driverId, Long routeId) {
        this.id = id;
        this.plate = plate;
        this.driverId = driverId;
        this.routeId = routeId;
    }

    public String getPlate() {
        return plate;
    }

    public Long getRouteId() {
        return routeId;
    }

    public Long getId() {
        return id;
    }

    public Long getDriverId() {
        return driverId;
    }
}
