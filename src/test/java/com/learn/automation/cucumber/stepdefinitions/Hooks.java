package com.learn.automation.cucumber.stepdefinitions;

import com.learn.automation.cucumber.TestContext;
import com.learn.automation.driver.DriverFactory;
import com.learn.automation.utils.ConfigReader;
import com.learn.automation.utils.TestDataReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    private final TestContext testContext;

    public Hooks(TestContext testContext){
        this.testContext=testContext;
    }

    @Before
    public void setUp()
    {
        ConfigReader.loadProperties("qa");
        TestDataReader.loadProperties("testdata");
        DriverFactory.createDriver(ConfigReader.getProperty("browser"));
    }

    @After
    public void tearDown(){
        DriverFactory.quitDriver();
    }

}
