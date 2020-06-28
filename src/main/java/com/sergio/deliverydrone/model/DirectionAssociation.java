package com.sergio.deliverydrone.model;

public class DirectionAssociation {

    private Direction left;
    private Direction right;

    public DirectionAssociation(Direction left, Direction right) {
        this.left = left;
        this.right = right;
    }

    public Direction getLeft() {
        return left;
    }

    public Direction getRight() {
        return right;
    }
}
