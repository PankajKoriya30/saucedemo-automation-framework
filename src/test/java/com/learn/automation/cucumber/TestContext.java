package com.learn.automation.cucumber;

import com.learn.automation.driver.DriverFactory;
import com.learn.automation.pages.*;
import org.openqa.selenium.WebDriver;

public class TestContext {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutInfoPage checkoutInfoPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private OrderConfirmationPage orderConfirmationPage;

    public WebDriver getDriver(){
        if(driver==null){
            driver = DriverFactory.getDriver();
        }
        return driver;
    }
    public LoginPage getLoginPage(){
        if(loginPage==null){
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }
    public ProductsPage getProductsPage(){
        if(productsPage==null){
            productsPage = new ProductsPage(driver);
        }
        return productsPage;
    }
    public CartPage getCartPage(){
        if(cartPage==null){
            cartPage = new CartPage(driver);
        }
        return cartPage;
    }

    public CheckoutInfoPage getCheckoutInfoPage(){
        if(checkoutInfoPage==null){
            checkoutInfoPage = new CheckoutInfoPage(driver);
        }
        return checkoutInfoPage;
    }

    public CheckoutOverviewPage getCheckoutOverviewPage(){
        if(checkoutOverviewPage==null){
            checkoutOverviewPage = new CheckoutOverviewPage(driver);
        }
        return checkoutOverviewPage;
    }

    public OrderConfirmationPage getOrderConfirmationPage(){
        if(orderConfirmationPage==null){
            orderConfirmationPage = new OrderConfirmationPage(driver);
        }
        return orderConfirmationPage;
    }
}
