package com.sergio.deliverydrone.file;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtilTest {

    private final static String PATH_TEST_INPUT = "input_test";

    @Test
    public void extractDroneName() {
        String testFile = "in01.txt";
        Path inputFile = lookupTestResource(testFile);

        String droneName = FileUtil.extractDroneName(inputFile);

        Assert.assertEquals( "01", droneName);
    }

    private Path lookupTestResource(String name) {
        try {
            return Paths.get(ClassLoader.getSystemResource(PATH_TEST_INPUT + File.separator + name).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

}
