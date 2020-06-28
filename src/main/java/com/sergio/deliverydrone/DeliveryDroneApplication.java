package com.sergio.deliverydrone;

import com.sergio.deliverydrone.file.DroneFileReader;
import com.sergio.deliverydrone.processor.DeliveryProcessor;

public class DeliveryDroneApplication {

    public static void main(String... args) {
        DeliveryProcessor processor = DeliveryProcessor.getInstance();
        DroneFileReader.readFiles()
                .parallelStream()
                .forEach(fileDrone -> {
                    processor.processFile(fileDrone);
                });
    }

}
