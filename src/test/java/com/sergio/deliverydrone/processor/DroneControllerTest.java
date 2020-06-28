package com.sergio.deliverydrone.processor;

import com.sergio.deliverydrone.exception.DeliveryDroneException;
import com.sergio.deliverydrone.model.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DroneControllerTest {

    @Test
    public void processDeliveryAllDirectionMovements() {
        Delivery circleTurnRight = buildDelivery("DDDDADADADA");
        Delivery circleTurnLeft = buildDelivery("IIIAIAIAIA");
        Delivery limitNavigation = buildDelivery("AAAAAAAAAADAAAAAAAAAA");
        Delivery outOfLimitGpsDelivery = buildDelivery("AAADAIIAA");
        Drone drone = new Drone("11");
        DroneController droneController = new DroneController(drone);

        DroneGps gpsPositionCircleTurnRight = droneController.processDelivery(circleTurnRight);
        Assertions.assertThat(gpsPositionCircleTurnRight)
                .hasFieldOrPropertyWithValue("x", 0)
                .hasFieldOrPropertyWithValue("y", 0)
                .hasFieldOrPropertyWithValue("direction", Direction.WEST);

        DroneGps gpsPositionCircleTurnLeft = droneController.processDelivery(circleTurnLeft);
        Assertions.assertThat(gpsPositionCircleTurnLeft)
                .hasFieldOrPropertyWithValue("x", 0)
                .hasFieldOrPropertyWithValue("y", 0)
                .hasFieldOrPropertyWithValue("direction", Direction.SOUTH);

        DroneGps gpsPositionLimitNavigation = droneController.processDelivery(limitNavigation);
        Assertions.assertThat(gpsPositionLimitNavigation)
                .hasFieldOrPropertyWithValue("x", 10)
                .hasFieldOrPropertyWithValue("y", 10)
                .hasFieldOrPropertyWithValue("direction", Direction.EAST);

        Throwable thrown = Assertions.catchThrowable(() -> droneController.processDelivery(outOfLimitGpsDelivery));
        Assertions.assertThat(thrown)
                .isInstanceOf(DeliveryDroneException.class)
                .hasMessage("Drone out of GPS limits.");
    }

    private static Delivery buildDelivery(String line) {
        List<Instruction> instructions = line.chars()
                .mapToObj(c -> (char) c)
                .map(String::valueOf)
                .map(Instruction::fromShortName)
                .collect(Collectors.toCollection(ArrayList::new));
        return new Delivery(instructions);

    }

}
