package com.sergio.deliverydrone.model;

import java.nio.file.Path;

public class FileDrone {

    private String droneName;
    private Path inputFile;
    private Path outputFile;

    public String getDroneName() {
        return droneName;
    }

    public void setDroneName(String droneName) {
        this.droneName = droneName;
    }

    public Path getInputFile() {
        return inputFile;
    }

    public void setInputFile(Path inputFile) {
        this.inputFile = inputFile;
    }

    public Path getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(Path outputFile) {
        this.outputFile = outputFile;
    }
}
