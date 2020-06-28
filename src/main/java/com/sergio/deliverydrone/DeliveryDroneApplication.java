package com.sergio.deliverydrone;

import com.sergio.deliverydrone.exception.DeliveryDroneException;
import com.sergio.deliverydrone.file.DeliveryDroneLogger;
import com.sergio.deliverydrone.file.DroneFileReader;
import com.sergio.deliverydrone.processor.DeliveryProcessor;

public class DeliveryDroneApplication {

    public static void main(String... args) {
        try {
            DeliveryProcessor processor = DeliveryProcessor.getInstance();
            DroneFileReader.readFiles()
                    .parallelStream()
                    .forEach(fileDrone -> {
                        processor.processFile(fileDrone);
                    });
        } catch(DeliveryDroneException dde) {
            DeliveryDroneLogger.printLog(dde);
        }
    }

}
