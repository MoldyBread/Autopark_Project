package com.autopark.entity;


import java.util.Objects;


/**
 *
 * Class that represents bus entity
 *
 * @author Liash Danylo
 */
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

    /**
     * plate getter
     * @return plate
     */
    public String getPlate() {
        return plate;
    }

    /**
     * routeId getter
     * @return routeId
     */
    public Long getRouteId() {
        return routeId;
    }

    /**
     * Id getter
     * @return Id
     */
    public Long getId() {
        return id;
    }

    /**
     * driverId getter
     * @return driverId
     */
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
