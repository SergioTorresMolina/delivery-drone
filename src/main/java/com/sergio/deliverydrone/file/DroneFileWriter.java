package com.sergio.deliverydrone.file;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class DroneFileWriter {

    private static final String OUTPUT_FORMAT = "out%s.txt";

    private static Path outputPath;

    static {
            outputPath = Paths.get(PropertyLoader.getPropertyValue("output_folder"));
    }

    public static void writeResult(String droneName, String result) {
        try {
            String location = outputPath.toString().concat(File.separator).concat(String.format(OUTPUT_FORMAT, droneName));
            Path outputPath = Paths.get(location);
            Files.deleteIfExists(outputPath);
            Files.write(outputPath, result.getBytes(), StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
