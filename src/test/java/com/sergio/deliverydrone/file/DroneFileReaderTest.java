package com.sergio.deliverydrone.file;

import com.sergio.deliverydrone.model.Delivery;
import com.sergio.deliverydrone.model.FileDrone;
import com.sergio.deliverydrone.model.Instruction;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DroneFileReaderTest {

    private final static String PATH_TEST_INPUT = "input";

    @Test
    public void readFilesTestShouldReturnValidFiles() {
        List<FileDrone> filesDrone = DroneFileReader.readFiles();

        Assertions.assertThat(filesDrone)
                .hasSize(2)
                .extracting("droneName")
                .containsOnly("01", "02");
    }

    @Test
    public void readFileContent() {
        Path fileInputPath = TestPathUtil.lookupTestResource(PATH_TEST_INPUT, "in01.txt");

        List<Delivery> deliveries = DroneFileReader.readFile(fileInputPath);

        //AAAAAIA
        List<Instruction> instructionDelivery1 = new ArrayList<>();
        instructionDelivery1.add(Instruction.ADVANCE);
        instructionDelivery1.add(Instruction.ADVANCE);
        instructionDelivery1.add(Instruction.ADVANCE);
        instructionDelivery1.add(Instruction.ADVANCE);
        instructionDelivery1.add(Instruction.ADVANCE);
        instructionDelivery1.add(Instruction.TURN_LEFT);
        instructionDelivery1.add(Instruction.ADVANCE);
        //ADAIAD
        List<Instruction> instructionDelivery2 = new ArrayList<>();
        instructionDelivery2.add(Instruction.ADVANCE);
        instructionDelivery2.add(Instruction.TURN_RIGHT);
        instructionDelivery2.add(Instruction.ADVANCE);
        instructionDelivery2.add(Instruction.TURN_LEFT);
        instructionDelivery2.add(Instruction.ADVANCE);
        instructionDelivery2.add(Instruction.TURN_RIGHT);
        Assertions.assertThat(deliveries)
                .hasSize(2)
                .extracting("instructions")
                .containsExactly(instructionDelivery1, instructionDelivery2);

    }
}
