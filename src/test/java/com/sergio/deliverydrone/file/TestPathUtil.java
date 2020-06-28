package com.sergio.deliverydrone.file;

import org.assertj.core.api.Assertions;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TestPathUtil {

    public static Path lookupTestResource(String folder ,String name) {
        try {
            return Paths.get(ClassLoader.getSystemResource(folder + File.separator + name).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void validateFileContent(List<String> lines, String fileName) throws Exception {
        String outputTestFolder = "output";
        Path filePath = Paths.get(ClassLoader.getSystemResource(outputTestFolder.concat(File.separator).concat(fileName)).toURI());
        List<String> actualFileLines = Files.readAllLines(filePath, Charset.defaultCharset());
        Assertions.assertThat(actualFileLines).containsExactlyElementsOf(lines);
    }

}
