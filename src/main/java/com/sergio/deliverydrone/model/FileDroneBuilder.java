package com.sergio.deliverydrone.model;

import java.nio.file.Path;

public class FileDroneBuilder {

    private FileDrone fileDrone;

    private FileDroneBuilder(Path inputFile) {
        fileDrone = new FileDrone();
        fileDrone.setInputFile(inputFile);
    }

    public static FileDroneBuilder builder(Path inputFile) {
        return new FileDroneBuilder(inputFile);
    }

    public FileDroneBuilder withDroneName(String droneName) {
        fileDrone.setDroneName(droneName);
        return this;
    }

    public FileDrone build() {
        return fileDrone;
    }

}
