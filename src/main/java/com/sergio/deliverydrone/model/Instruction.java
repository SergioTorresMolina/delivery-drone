package com.sergio.deliverydrone.model;

import com.sergio.deliverydrone.exception.DeliveryDroneException;
import com.sergio.deliverydrone.exception.DeliveryDroneExceptionType;

import java.util.Arrays;
import java.util.function.Consumer;

public enum Instruction {

    ADVANCE("A", Addressable::move),
    TURN_LEFT("I", Addressable::turnLeft),
    TURN_RIGHT("D", Addressable::turnRight);

    private String shortName;
    private Consumer action;

    Instruction(String shortName, Consumer<Addressable> action) {
        this.shortName = shortName;
        this.action = action;
    }

    public static Instruction fromShortName(String shortName) throws DeliveryDroneException {
        return Arrays.asList(Instruction.values())
                .stream()
                .filter(instruction -> shortName.equals(instruction.getShortName()))
                .findFirst()
                .orElseThrow(() -> new DeliveryDroneException(DeliveryDroneExceptionType.INSTRUCTION_INVALID, "Instruction unrecognized " + shortName));
    }

    public String getShortName() {
        return shortName;
    }

    public Consumer getAction() {
        return action;
    }
}
