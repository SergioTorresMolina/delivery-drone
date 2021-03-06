package com.sergio.deliverydrone.processor;

import com.sergio.deliverydrone.exception.DeliveryDroneException;
import com.sergio.deliverydrone.file.DeliveryDroneLogger;
import com.sergio.deliverydrone.file.DroneFileReader;
import com.sergio.deliverydrone.file.DroneFileWriter;
import com.sergio.deliverydrone.model.Delivery;
import com.sergio.deliverydrone.model.DroneFactory;
import com.sergio.deliverydrone.model.DroneGps;
import com.sergio.deliverydrone.model.FileDrone;

import java.util.List;
import java.util.stream.Collectors;

public class DeliveryProcessor {

    private static DeliveryProcessor instance;
    private DroneFactory droneFactory;

    private DeliveryProcessor() {
        droneFactory = DroneFactory.getInstance();
    }

    public static DeliveryProcessor getInstance() {
        if(null == instance) {
            instance = new DeliveryProcessor();
        }
        return instance;
    }

    public void processFile(FileDrone fileDrone) {
        List<Delivery> fileDeliveries;
        try {
             fileDeliveries = DroneFileReader.readFile(fileDrone.getInputFile());
        }catch(DeliveryDroneException dde) {
            DeliveryDroneLogger.printLog(String.format("Error: %s, %s", dde.getType().name(), dde.getMessage()));
            return;
        }
        DroneController droneController = new DroneController(droneFactory.getDrone(fileDrone.getDroneName()));
        String result;
        if(!validateDeliveriesLength(fileDeliveries)) {
            result = "Error: Invalid number of deliveries";
        } else {
            result = fileDeliveries.stream()
                    .map(delivery -> processDelivery(delivery, droneController))
                    .collect(Collectors.joining("\n"));
        }
        DroneFileWriter.writeResult(fileDrone.getDroneName(), result);
        DeliveryDroneLogger.printLog("Drone ".concat(fileDrone.getDroneName()).concat(":\n").concat(result).concat("\n"));
    }

    private boolean validateDeliveriesLength(List<Delivery> deliveries) {
        return deliveries.size() < 3 && deliveries.size() > 0;
    }

    private String processDelivery(Delivery delivery, DroneController droneController) {
        try {
            DroneGps finalLocation = droneController.processDelivery(delivery);
            return finalLocation.toString();
        } catch (DeliveryDroneException e) {
            return "Error: " + e.getMessage();
        }
    }

}
