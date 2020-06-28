package com.sergio.deliverydrone.exception;

public class DeliveryDroneException extends RuntimeException {

    private DeliveryDroneExceptionType type;

    public DeliveryDroneException(DeliveryDroneExceptionType type, String message) {
        super(message);
        this.type = type;
    }

    public DeliveryDroneException(DeliveryDroneExceptionType type, String message, Exception exception) {
        super(message, exception);
        this.type = type;
    }

}
