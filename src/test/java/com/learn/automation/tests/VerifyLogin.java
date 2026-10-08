package com.learn.automation.tests;

import com.learn.automation.pages.LoginPage;
import com.learn.automation.pages.ProductsPage;
import com.learn.automation.utils.TestDataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyLogin extends BaseTest{

        private LoginPage loginPage;

        @Test(groups = "regression")
        public void verifySuccessLogin(){
            loginPage = new LoginPage(driver);
            loginPage.enterUsername(TestDataReader.getTestData("username"));
            loginPage.enterPassword(TestDataReader.getTestData("password"));
            ProductsPage productsPage = loginPage.clickLogin();

            String productsPageTitle = productsPage.getProductsPageTitle();
            Assert.assertEquals(productsPageTitle, "Products");
            String title = driver.getTitle();
            Assert.assertEquals(title, "Swag Labs");
        }
}