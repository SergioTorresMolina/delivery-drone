package com.sergio.deliverydrone.processor;

import com.sergio.deliverydrone.file.TestPathUtil;
import com.sergio.deliverydrone.model.FileDrone;
import com.sergio.deliverydrone.model.FileDroneBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DeliveryProcessorTest {

    @Test
    public void processFileShouldWriteFilesResultSuccess() throws Exception {
        FileDrone input = FileDroneBuilder.builder(TestPathUtil.lookupTestResource("input", "in02.txt"))
                .withDroneName("02")
                .build();

        DeliveryProcessor.getInstance().processFile(input);

        List<String> linesFileExpected = new ArrayList<>();
        linesFileExpected.add("(0, 5) Dirección Oeste");
        linesFileExpected.add("(2, 2) Dirección Norte");
        TestPathUtil.validateFileContent(linesFileExpected, "out02.txt");
    }

    @Test
    public void processFileShouldWriteFilesResultWithGpsLimitsError() throws Exception {
        FileDrone input = FileDroneBuilder.builder(TestPathUtil.lookupTestResource("input", "in07.txt"))
                .withDroneName("07")
                .build();

        DeliveryProcessor.getInstance().processFile(input);

        List<String> linesFileExpected = new ArrayList<>();
        linesFileExpected.add("Error: Drone out of GPS limits.");
        linesFileExpected.add("(2, 2) Dirección Norte");
        TestPathUtil.validateFileContent(linesFileExpected, "out07.txt");
    }

    @Test
    public void processFileShouldWriteErrorDeliveriesExceeded() throws Exception {
        FileDrone input = FileDroneBuilder.builder(TestPathUtil.lookupTestResource("input", "in17.txt"))
                .withDroneName("17")
                .build();

        DeliveryProcessor.getInstance().processFile(input);

        List<String> linesFileExpected = new ArrayList<>();
        linesFileExpected.add("Error: Invalid number of deliveries");
        TestPathUtil.validateFileContent(linesFileExpected, "out17.txt");
    }
}
