package com.learn.automation.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static final Logger logger =
            LoggerFactory.getLogger(DriverFactory.class);

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver createDriver(String browser){

        if (browser==null || browser.trim().isEmpty()){
            logger.error("Browser is not configured");
            throw new IllegalArgumentException("Browser is not configured.");
        }

        logger.info("Creating {} browser", browser);
        switch (browser.trim().toLowerCase()){
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<>();

                // Disable Chrome password manager
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false);
                options.setExperimentalOption("prefs", prefs);
                driver.set(new ChromeDriver(options));
                break;
            case "firefox":
                driver.set(new FirefoxDriver());
                break;
            case "edge":
                driver.set(new EdgeDriver());
                break;
            default:
                logger.error("Unsupported browser: {}", browser);
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.get().manage().window().maximize();
        logger.info("Browser {} is started successfully", browser);
        return driver.get();
    }

    public static WebDriver getDriver()
    {
        logger.info("Get current {} driver object", driver.get());
        return driver.get();
    }
    public static void quitDriver(){
        if(driver.get()!=null){
            logger.info("Closing the browser session");
            driver.get().quit();
            driver.remove();
            logger.info("Browser closed successfully");
        }
    }
}
