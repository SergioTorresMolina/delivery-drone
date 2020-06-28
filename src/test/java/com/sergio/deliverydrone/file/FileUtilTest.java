package com.sergio.deliverydrone.file;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;

public class FileUtilTest {

    private final static String PATH_TEST_INPUT = "input";

    @Test
    public void extractDroneName() {
        String testFile = "in01.txt";
        Path inputFile = TestPathUtil.lookupTestResource(PATH_TEST_INPUT, testFile);

        String droneName = FileUtil.extractDroneName(inputFile);

        Assert.assertEquals( "01", droneName);
    }

}
