package com.learn.automation.cucumber.stepdefinitions;

import com.learn.automation.cucumber.TestContext;
import com.learn.automation.driver.DriverFactory;
import com.learn.automation.pages.LoginPage;
import com.learn.automation.pages.ProductsPage;
import com.learn.automation.utils.ConfigReader;
import com.learn.automation.utils.TestDataReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginStepDef extends TestContext {

    private final TestContext testContext;

    public LoginStepDef(TestContext testContext){
        this.testContext=testContext;
    }

    @Given("user is on the sauce demo login page")
    public void userIsOnTheSauceDemoLoginPage(){
        testContext.getDriver().get(ConfigReader.getProperty("url"));
    }

    @When("user enters valid credentials")
    public void userEntersValidCredentials(){
        testContext.getLoginPage().enterUsername(TestDataReader.getTestData("username"));
        testContext.getLoginPage().enterPassword(TestDataReader.getTestData("password"));
        testContext.getLoginPage().clickLogin();

    }

    @Then("user redirects to products page")
    public void userRedirectsToProductsPage(){
        Assert.assertTrue(testContext.getProductsPage().getProductsPageTitle().equalsIgnoreCase("Products"),
                "Products page is not displayed after login");
    }
}
