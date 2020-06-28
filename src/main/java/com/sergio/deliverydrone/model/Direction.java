package com.sergio.deliverydrone.model;

public enum Direction {

    NORTH("Y", 1, "Norte"),
    SOUTH("Y", -1, "Sur"),
    WEST("X", -1, "Oeste"),
    EAST("X", 1, "Este");

    private String axis;
    private int way;
    private String label;

    Direction(String axis, int way, String label) {
        this.axis = axis;
        this.way = way;
        this.label = label;
    }

    public String getAxis() {
        return axis;
    }

    public int getWay() {
        return way;
    }

    public String getLabel() {
        return label;
    }
}
