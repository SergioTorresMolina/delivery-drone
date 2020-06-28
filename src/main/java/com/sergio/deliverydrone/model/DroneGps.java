package com.sergio.deliverydrone.model;

import com.sergio.deliverydrone.exception.DeliveryDroneException;
import com.sergio.deliverydrone.exception.DeliveryDroneExceptionType;
import com.sergio.deliverydrone.file.PropertyLoader;
import com.sergio.deliverydrone.processor.TurnController;

import java.io.Serializable;

public class DroneGps implements Serializable, Addressable {

    private static Integer MAX_BLOCKS;

    private Integer maxYValue;
    private Integer minYValue;
    private Integer maxXValue;
    private Integer minXValue;
    private Integer x;
    private Integer y;
    private Direction direction;

    public DroneGps() {
        MAX_BLOCKS = Integer.valueOf(PropertyLoader.getPropertyValue("blocks"));
        x = 0;
        y = 0;
        direction = Direction.NORTH;
        minYValue = 0;
        maxYValue = MAX_BLOCKS;
        minXValue = 0;
        maxXValue = MAX_BLOCKS;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void turnRight() {
        direction = TurnController.turnRight(direction);
    }

    public void turnLeft() {
        direction = TurnController.turnLeft(direction);
    }

    public void move() {
        if("Y".equals(direction.getAxis())) {
           y =  Integer.sum(y, direction.getWay());
        } else {
            x = Integer.sum(x, direction.getWay());
        }
        if(!isValidLocation()) {
            throw new DeliveryDroneException(DeliveryDroneExceptionType.OUT_OF_LIMITS, "Drone out of GPS limits.");
        }
    }

    private boolean isValidLocation() {
        if(y >= minYValue && y <= maxYValue
        && x >= minXValue && x <= maxXValue) {
            return true;
        }
        return false;
    }

    public void backToOrigin() {
        x = 0;
        y = 0;
        direction = Direction.NORTH;
    }

    public String toString() {
        return String.format("(%d, %d) Dirección %s", x, y, direction.getLabel());
    }

}
