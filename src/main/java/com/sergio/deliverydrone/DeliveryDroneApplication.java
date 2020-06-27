package com.sergio.deliverydrone;

import com.sergio.deliverydrone.file.DroneFileReader;

public class DeliveryDroneApplication {

    public static void main(String... args) {
        System.out.println(DroneFileReader.readFiles());
    }

}
