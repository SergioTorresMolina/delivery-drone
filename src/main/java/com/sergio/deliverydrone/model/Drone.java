package com.sergio.deliverydrone.model;

import java.io.Serializable;

public class Drone implements Serializable {

    private String name;
    private DroneGps droneGps;

    public Drone(String name) {
        this.name = name;
        this.droneGps = new DroneGps();
    }

    public String getName() {
        return name;
    }

    public DroneGps getDroneGps() {
        return droneGps;
    }

}
