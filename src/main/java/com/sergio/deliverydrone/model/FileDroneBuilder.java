package com.sergio.deliverydrone.model;

import java.io.Serializable;
import java.nio.file.Path;

public class FileDroneBuilder implements Serializable {

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
