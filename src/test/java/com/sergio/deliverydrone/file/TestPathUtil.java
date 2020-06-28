package com.sergio.deliverydrone.file;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestPathUtil {

    public static Path lookupTestResource(String folder ,String name) {
        try {
            return Paths.get(ClassLoader.getSystemResource(folder + File.separator + name).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

}
