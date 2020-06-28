package com.sergio.deliverydrone.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DroneFactory {

    private Map<String, Drone> activeInventory;

    private static DroneFactory instance;

    public DroneFactory() {
        activeInventory = new HashMap<>();
    }

    public static DroneFactory getInstance() {
        if(null == instance) {
            instance = new DroneFactory();
        }
        return instance;
    }

    public Drone getDrone(String name) {
        return Optional.ofNullable(activeInventory.get(name))
                .orElseGet(() -> buildDrone(name));
    }

    private Drone buildDrone(String name) {
        Drone drone = new Drone(name);
        activeInventory.put(name, drone);
        return drone;
    }

}
