package com.autopark.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Class that represents route entity
 *
 * @author Liash Danylo
 */
public class Route {
    private Long id;
    private int mileage;
    private List<String> busPlates;

    public Route(Long id, int mileage) {
        this.id = id;
        this.mileage = mileage;
        this.busPlates = new ArrayList<>();
    }

    /**
     * Adds bus to route
     * @param plate of bus that is add
     */
    public void addBus(String plate) {
        busPlates.add(plate);
    }

    /**
     * Id getter
     * @return Id
     */
    public Long getId() {
        return id;
    }

    /**
     * Mileage getter
     * @return Mileage
     */
    public int getMileage() {
        return mileage;
    }

    /**
     * Bus plates list getter
     * @return Bus plates list
     */
    public List<String> getBusPlates() {
        return busPlates;
    }
}
