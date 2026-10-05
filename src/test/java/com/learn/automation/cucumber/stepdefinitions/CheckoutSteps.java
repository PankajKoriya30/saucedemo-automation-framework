package com.learn.automation.cucumber.stepdefinitions;

import com.learn.automation.cucumber.TestContext;
import com.learn.automation.utils.TestDataReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class CheckoutSteps {

    private final TestContext testContext;
    private static final Logger logger =
            LoggerFactory.getLogger(CheckoutSteps.class);

    public CheckoutSteps(TestContext testContext){
        this.testContext=testContext;
    }

    @And("user adds backpack to the cart")
    public void userAddsBackpackToTheCart()
    {
        logger.info("Adding backpack to the cart");
        testContext.getProductsPage().clickProductAddToCartButton(
                TestDataReader.getTestData("backpack")
        );
        logger.info("Backpack is added to the cart");
    }

    @And("user adds bike light to the cart")
    public void userAddsBikeLightToTheCart()
    {
        logger.info("Adding bike light to the cart");
        testContext.getProductsPage().clickProductAddToCartButton(
                TestDataReader.getTestData("bikelight")
        );
        logger.info("Bike light is added to the cart");
    }

    @And("user opens shopping cart")
    public void useOpensShoppingCart(){
        logger.info("Navigating to cart page");
        testContext.getProductsPage().openShoppingCart();
        logger.info("Cart page is opened successfully");
    }

    @Then("backpack and bike light should be displayed in the cart page")
    public void backpackAndBikeLightShouldBeDisplayedInTheCartPage(){
        Assert.assertTrue(testContext.getCartPage().getCartProductNames().contains(
                TestDataReader.getTestData("backpack")),
                "Backpack is not added to the cart"
        );
        Assert.assertTrue(testContext.getCartPage().getCartProductNames().contains(
                        TestDataReader.getTestData("bikelight")),
                "Bike light is not added to the cart"
        );
        logger.info("Backpack and Bike light added to the cart successfully");
    }

    @And("user checkout the products from cart page")
    public void userCheckoutTheProductsFromCartPage() {
        testContext.getCartPage().clickCheckout();
        logger.info("Backpack and Bike light checked out successfully");
    }

    @And("user enters checkout information")
    public void userEntersCheckoutInformation() {
        logger.info("Entering checkout information");
        testContext.getCheckoutInfoPage().enterFirstName(TestDataReader.getTestData("firstname"));
        testContext.getCheckoutInfoPage().enterLastName(TestDataReader.getTestData("lastname"));
        testContext.getCheckoutInfoPage().enterZipCode(TestDataReader.getTestData("zipcode"));
        logger.info("Checkout information added successfully");
    }

    @And("user clicks continue")
    public void userClicksContinue() {
        logger.info("Navigating to checkout overview page");
        testContext.getCheckoutInfoPage().clickContinue();
        logger.info("Navigated to checkout overview page");
    }

    @And("user clicks finish from checkout overview page")
    public void userClicksFinishFromCheckoutOverviewPage() {
        logger.info("Placing the order by clicking on finish button");
        testContext.getCheckoutOverviewPage().clickFinish();
    }

    @Then("user should be redirected to confirmation page")
    public void userShouldBeRedirectedToConfirmationPage() {
        Assert.assertEquals(testContext.getOrderConfirmationPage().getOrderConfirmationMessage(),
        "Thank you for your order!",
                "User is not redirected to the order confirmation page.");
        logger.info("Checkout is successful");

    }
}