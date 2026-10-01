package com.learn.automation.cucumber.stepdefinitions;

import com.learn.automation.cucumber.TestContext;
import com.learn.automation.utils.ConfigReader;
import com.learn.automation.utils.ScreenshotUtils;
import com.learn.automation.utils.TestDataReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class LoginSteps {

    private final TestContext testContext;
    private static final Logger logger = LoggerFactory.getLogger(LoginSteps.class);

    public LoginSteps(TestContext testContext){
        this.testContext=testContext;
    }

    @Given("user is on the sauce demo login page")
    public void userIsOnTheSauceDemoLoginPage(){
        logger.info("Navigating to sauce demo login page");
        testContext.getDriver().get(ConfigReader.getProperty("url"));
        logger.info("Sauce demo login is opened");
    }

    @When("user enters valid credentials")
    public void userEntersValidCredentials(){
        logger.info("Entering login credentials");
        testContext.getLoginPage().enterUsername(TestDataReader.getTestData("username"));
        testContext.getLoginPage().enterPassword(TestDataReader.getTestData("password"));
        testContext.getLoginPage().clickLogin();

    }

    @Then("products page should be displayed")
    public void productsPageShouldBeDisplayed() throws IOException {
        Assert.assertTrue(testContext.getProductsPage().getProductsPageTitle().equalsIgnoreCase("Products"),
                "Products page is not displayed after login");
        logger.info("Login success and navigated to products page");

//        String screenshotPath = ScreenshotUtils.captureScreenshot(
//                testContext.getDriver(), "login_Success");
//        logger.info("Screenshot captured: {}", screenshotPath);
    }
}
