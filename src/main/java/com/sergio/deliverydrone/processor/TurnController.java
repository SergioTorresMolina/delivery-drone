package com.sergio.deliverydrone.processor;

import com.sergio.deliverydrone.model.Direction;
import com.sergio.deliverydrone.model.DirectionAssociation;

import java.util.HashMap;
import java.util.Map;

public class TurnController {

    private static final Map<Direction, DirectionAssociation> directionAssociationMap;

    static {
        directionAssociationMap = new HashMap<>();
        directionAssociationMap.put(Direction.NORTH, new DirectionAssociation(Direction.WEST, Direction.EAST));
        directionAssociationMap.put(Direction.SOUTH, new DirectionAssociation(Direction.EAST, Direction.WEST));
        directionAssociationMap.put(Direction.WEST, new DirectionAssociation(Direction.SOUTH, Direction.NORTH));
        directionAssociationMap.put(Direction.EAST, new DirectionAssociation(Direction.NORTH, Direction.SOUTH));
    }

    public static Direction turnLeft(Direction origin) {
        return directionAssociationMap.get(origin).getLeft();
    }

    public static Direction turnRight(Direction origin) {
        return directionAssociationMap.get(origin).getRight();
    }

}
