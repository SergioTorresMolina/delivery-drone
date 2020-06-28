package com.sergio.deliverydrone.file;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DroneFileWriterTest {

    @Test
    public void writeResult() throws Exception {
        String result = "(9, 8) Dirección Oeste" +
                "\n(8, 7) Dirección Norte";

        DroneFileWriter.writeResult("15", result);

        List<String> expectedLinesFile = new ArrayList<>();
        expectedLinesFile.add("(9, 8) Dirección Oeste");
        expectedLinesFile.add("(8, 7) Dirección Norte");
        TestPathUtil.validateFileContent(expectedLinesFile, "out15.txt");
    }

}
