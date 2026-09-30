package com.learn.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import  org.slf4j.LoggerFactory;

public class ConfigReader {

    private static Properties properties;
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);

    public static void loadProperties(String environment){

        logger.info("Setting up {} property file", environment);
        properties = new Properties();
        try{
        FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\config\\"
                + environment +".properties");
        properties.load(fis);
        logger.info("Setup is successful for {} property file", environment);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load configuration file: "
                     + environment, e);
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
