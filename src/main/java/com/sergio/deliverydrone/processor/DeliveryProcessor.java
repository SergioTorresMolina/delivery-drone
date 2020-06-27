package com.sergio.deliverydrone.processor;

import java.nio.file.Path;

public class DeliveryProcessor {

    private static DeliveryProcessor instance;

    private DeliveryProcessor() {
    }

    public static DeliveryProcessor getInstance() {
        if(null == instance) {
            instance = new DeliveryProcessor();
        }
        return instance;
    }

    public void processFile(Path inputFile) {

    }

}
