package com.sergio.deliverydrone.file;

import com.sergio.deliverydrone.model.FileDrone;
import com.sergio.deliverydrone.model.FileDroneBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DroneFileReader {

    private static final String INPUT_DRONE_PATH = "input";

    private static final Pattern FILE_PATTERN = Pattern.compile("in\\d[1-9].txt");

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

}
