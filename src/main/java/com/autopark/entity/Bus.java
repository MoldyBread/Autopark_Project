package com.autopark.entity;


import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return Objects.equals(id, bus.id) &&
                Objects.equals(plate, bus.plate) &&
                Objects.equals(driverId, bus.driverId) &&
                Objects.equals(routeId, bus.routeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, plate, driverId, routeId);
    }
}
