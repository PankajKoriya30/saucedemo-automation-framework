package com.learn.automation.cucumber.stepdefinitions;

import com.learn.automation.cucumber.TestContext;
import com.learn.automation.utils.TestDataReader;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class ChekoutStepDef {

    private final TestContext testContext;

    public ChekoutStepDef(TestContext testContext){
        this.testContext=testContext;
    }

    @And("user adds backpack to the cart")
    public void userAddsBackpackToTheCart()
    {
        testContext.getProductsPage().clickProductAddToCartButton(
                TestDataReader.getTestData("backpack")
        );
    }

    @And("user adds bike light to the cart")
    public void userAddsBikeLightToTheCart()
    {
        testContext.getProductsPage().clickProductAddToCartButton(
                TestDataReader.getTestData("bikelight")
        );
    }

    @And("user opens shopping cart")
    public void useOpensShoppingCart(){
        testContext.getProductsPage().openShoppingCart();
    }

    @Then("backpack and bike light should be displayed in the cart page")
    public void backpackAndBikeLightShouldBeDisplayedInTheCartPage(){
        Assert.assertTrue(testContext.getCartPage().getCartProductNames().contains(
                TestDataReader.getTestData("backpack")),
                "Back pack is not added to the cart"
        );
        Assert.assertTrue(testContext.getCartPage().getCartProductNames().contains(
                        TestDataReader.getTestData("bikelight")),
                "Bike light is not added to the cart"
        );
    }

    @And("user checkout the products from cart page")
    public void userCheckoutTheProductsFromCartPage() {
        testContext.getCartPage().clickCheckout();
    }

    @And("user enters checkout information")
    public void userEntersCheckoutInformation() {
        testContext.getCheckoutInfoPage().enterFirstName(TestDataReader.getTestData("firstname"));
        testContext.getCheckoutInfoPage().enterLastName(TestDataReader.getTestData("lastname"));
        testContext.getCheckoutInfoPage().enterZipCode(TestDataReader.getTestData("zipcode"));
    }

    @And("user clicks continue")
    public void userClicksContinue() {
        testContext.getCheckoutInfoPage().clickContinue();
    }

    @And("user clicks finish from checkout overview page")
    public void userClicksFinishFromCheckoutOverviewPage() {
        testContext.getCheckoutOverviewPage().clickFinish();
    }

    @Then("user should be redirected to confirmation page")
    public void userShouldBeRedirectedToConfirmationPage() {
        Assert.assertEquals(testContext.getOrderConfirmationPage().getOrderConfirmationMessage(),
        "Thank you for your order!",
                "User is not redirected to the order confirmation page.");

    }
}