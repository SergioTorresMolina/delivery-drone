package com.sergio.deliverydrone.model;

import java.io.Serializable;
import java.util.List;

public class Delivery  implements Serializable {

    private List<Instruction> instructions;

    public Delivery(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

}
