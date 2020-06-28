package com.sergio.deliverydrone.file;

import com.sergio.deliverydrone.exception.DeliveryDroneException;
import com.sergio.deliverydrone.exception.DeliveryDroneExceptionType;
import com.sergio.deliverydrone.model.Delivery;
import com.sergio.deliverydrone.model.FileDrone;
import com.sergio.deliverydrone.model.FileDroneBuilder;
import com.sergio.deliverydrone.model.Instruction;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DroneFileReader {

    private static final String INPUT_DRONE_PATH = "input";

    private static final Pattern FILE_PATTERN = Pattern.compile("in\\d[0-9].txt");

    private static Path inputPath;

    static {
        try {
            inputPath = Paths.get(ClassLoader.getSystemResource(INPUT_DRONE_PATH).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static List<FileDrone> readFiles() {
        try {
            return Files.list(inputPath)
                    .filter(path -> FileUtil.isValidFile(path, FILE_PATTERN))
                    .map(DroneFileReader::buildFileDrone)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static FileDrone buildFileDrone(Path fileInput) {
        return FileDroneBuilder.builder(fileInput)
                .withDroneName(FileUtil.extractDroneName(fileInput))
                .build();
    }

    public static List<Delivery> readFile(Path inputFileDelivery) {
        try {
            return Files.readAllLines(inputFileDelivery, Charset.defaultCharset())
                    .stream()
                    .map(DroneFileReader::buildDelivery)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new DeliveryDroneException(DeliveryDroneExceptionType.IO_EXCEPTION, "Error reading Drone File " + inputFileDelivery.getFileName().toString(), e);
        }
    }

    private static Delivery buildDelivery(String line) {
        List<Instruction> instructions = line.chars()
                .mapToObj(c -> (char) c)
                .map(String::valueOf)
                .map(Instruction::fromShortName)
                .collect(Collectors.toCollection(ArrayList::new));
        return new Delivery(instructions);

    }

}
