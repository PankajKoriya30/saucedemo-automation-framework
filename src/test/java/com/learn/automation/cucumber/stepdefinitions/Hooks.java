package com.learn.automation.cucumber.stepdefinitions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.learn.automation.cucumber.TestContext;
import com.learn.automation.driver.DriverFactory;
import com.learn.automation.utils.ConfigReader;
import com.learn.automation.utils.ExtentReportManager;
import com.learn.automation.utils.ScreenshotUtils;
import com.learn.automation.utils.TestDataReader;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;


public class Hooks {

    private static final Logger logger =
            LoggerFactory.getLogger(Hooks.class);
    private final TestContext testContext;
    private static ExtentTest extentTest;

    public Hooks(TestContext testContext){
        this.testContext = testContext;
    }

    @Before
    public void setUp(Scenario scenario) {
        extentTest = ExtentReportManager.getExtentReports().createTest(scenario.getName());
        logger.info("Starting Cucumber scenario setup");
        ConfigReader.loadProperties("qa");
        TestDataReader.loadProperties("testdata");
        logger.info("Configuring browser for cucumber scenario");
        DriverFactory.createDriver(ConfigReader.getProperty("browser"));
        logger.info("Cucumber scenario setup completed");
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        logger.info("Starting Cucumber scenario teardown");
        if (scenario.isFailed()){
            logger.error("Scenario failed: {}", scenario.getName());
            String screenshotName = scenario.getName().replaceAll("[^a-zA-Z0-9-_]", "_");
            String screenshotPath = ScreenshotUtils.captureScreenshot(testContext.getDriver(),
                    screenshotName);
            logger.error("Failure screenshot captured: {}", screenshotPath);

            byte[] screenshot = ((TakesScreenshot) testContext.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", screenshotName);
            extentTest.fail("Screnario Failed.", MediaEntityBuilder
                            .createScreenCaptureFromPath(screenshotPath)
                            .build());
        }
        else {
            extentTest.pass("Scenario passed.");
        }
        DriverFactory.quitDriver();
        logger.info("Cucumber scenario teardown completed");
    }

    @AfterAll
    public static void generateExtentReport(){
        ExtentReportManager.getExtentReports().flush();
    }
}
