package com.sergio.deliverydrone.file;

import com.sergio.deliverydrone.exception.DeliveryDroneException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class DeliveryDroneLogger {

    private static final String LOG_FILE = "application.log";

    private static Path outputPath;

    static {
        outputPath = Paths.get(PropertyLoader.getPropertyValue("output_folder")+ File.separator + LOG_FILE);
        try {
            Files.deleteIfExists(outputPath);
            Files.write(outputPath, "DRONE_DELIVERY_APPLICATION\n".getBytes(), StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printLog(DeliveryDroneException exception){
        String exceptionLine = String.format("ERROR: %s, %s", exception.getType().name(), exception.getMessage());
    }

    public static void printLog(String message) {
        try {
            Files.write(outputPath, message.concat("\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
