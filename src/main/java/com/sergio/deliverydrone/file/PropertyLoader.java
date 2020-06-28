package com.sergio.deliverydrone.file;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class PropertyLoader {

    private static Properties p;

    public static String getPropertyValue(String propertyName) {
        if(null == p) {
            loadProperties();
        }
        return p.getProperty(propertyName);
    }

    private static void loadProperties() {
        try {
            p = new Properties();
            FileReader reader = new FileReader(new File(ClassLoader.getSystemResource("application.properties").toURI()));
            p.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
