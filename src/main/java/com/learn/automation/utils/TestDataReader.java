package com.learn.automation.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import  org.slf4j.LoggerFactory;

public class TestDataReader {

    private static Properties properties;
    private static final Logger logger = LoggerFactory.getLogger(TestDataReader.class);

    public static void loadProperties(String testData){

        logger.info("Setting up {} property file", testData);
        properties = new Properties();
        try{
        FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\testdata\\"
                + testData +".properties");
        properties.load(fis);
            logger.info("Setup is successful for {} property file", testData);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load test data file: "
                     + testData, e);
        }
    }

    public static String getTestData(String key){
        return properties.getProperty(key);
    }
}
