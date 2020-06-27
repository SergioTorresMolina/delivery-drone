package com.sergio.deliverydrone.file;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {

    public static boolean isValidFile(Path inputPath, Pattern droneFileInputPattern) {
        return isValidFileName(inputPath.getFileName().toString(), droneFileInputPattern) &&
                Files.isRegularFile(inputPath);
    }

    private static boolean isValidFileName(String inputFileName, Pattern droneFileInputPattern) {
        Matcher matcher = droneFileInputPattern.matcher(inputFileName);
        return matcher.find();
    }

    public static String extractDroneName(Path inputPath) {
        String fileName = inputPath.getFileName().toString();
        return fileName.substring(fileName.indexOf("in") + 2, fileName.indexOf(".txt"));
    }

}
