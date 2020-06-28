package com.sergio.deliverydrone.processor;

import com.sergio.deliverydrone.model.Delivery;
import com.sergio.deliverydrone.model.Drone;
import com.sergio.deliverydrone.model.DroneGps;

public class DroneController {

    private Drone drone;

    public DroneController(Drone drone) {
        this.drone = drone;
    }

    public DroneGps processDelivery(Delivery delivery) {
        drone.getDroneGps().backToOrigin();
        delivery.getInstructions()
                .forEach(instruction -> instruction.getAction().accept(drone.getDroneGps()));
        return drone.getDroneGps();
    }
}
